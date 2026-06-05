package com.m2l.m2l.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.m2l.m2l.dto.CreateAuthor;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.enums.Langage;
import com.m2l.m2l.services.ArticleService;
import com.m2l.m2l.services.AuthorService;
import com.m2l.m2l.services.BookServiceImpl;
import com.m2l.m2l.services.CartItemService;
import com.m2l.m2l.services.CommandeService;
import com.m2l.m2l.services.EditorService;
import com.m2l.m2l.services.FactureService;
import com.m2l.m2l.services.SaleService;
import com.m2l.m2l.services.TokenService;
import com.m2l.m2l.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerUnitTest {
        @Autowired
        private MockMvc mockMvc;
        @MockitoBean
        private AuthorService authorService;
        @MockitoBean
        private BookServiceImpl bookService;
        @MockitoBean
        private ArticleService articleService;
        @MockitoBean
        private CartItemService cartItemService;
        @MockitoBean
        private CommandeService commandeService;
        @MockitoBean
        private EditorService editorService;
        @MockitoBean
        private FactureService factureService;
        @MockitoBean
        private SaleService saleService;
        @MockitoBean
        private UserService UserService;
        @MockitoBean
        private TokenService tokenService;
        @MockitoBean
        private UserDetailsService userDetailsService;

        @Test
        @WithMockUser(username = "doe", roles = { "USER" })
        void testGetAuthorUser() throws Exception {
                List<Author> fakedAuthors = List.of(
                                new Author().builder()
                                                .lastname("Asimov")
                                                .firstname("Isaac")
                                                .langue(Langage.DE)
                                                .build(),
                                new Author().builder()
                                                .lastname("K Dick")
                                                .firstname("Philip")
                                                .langue(Langage.ES)
                                                .build(),
                                new Author().builder()
                                                .lastname("Doe")
                                                .firstname("John")
                                                .langue(Langage.GB)
                                                .build(),
                                new Author().builder()
                                                .lastname("Dupont")
                                                .firstname("Martin")
                                                .langue(Langage.FR)
                                                .build());
                Page<Author> fakedPageAuthor = new PageImpl<>(fakedAuthors);
                when(authorService.findAll()).thenReturn(fakedPageAuthor);

                mockMvc
                                .perform(get("/author/all"))
                                .andExpect(status().is(200));
        }

        @Test
        void testGetAuthorNoUser() throws Exception {
                List<Author> fakedAuthors = List.of(
                                new Author().builder()
                                                .lastname("Asimov")
                                                .firstname("Isaac")
                                                .langue(Langage.DE)
                                                .build(),
                                new Author().builder()
                                                .lastname("K Dick")
                                                .firstname("Philip")
                                                .langue(Langage.ES)
                                                .build(),
                                new Author().builder()
                                                .lastname("Doe")
                                                .firstname("John")
                                                .langue(Langage.GB)
                                                .build(),
                                new Author().builder()
                                                .lastname("Dupont")
                                                .firstname("Martin")
                                                .langue(Langage.FR)
                                                .build());
                Page<Author> fakedPageAuthor = new PageImpl<>(fakedAuthors);
                when(authorService.findAll()).thenReturn(fakedPageAuthor);

                mockMvc
                                .perform(get("/author/all"))
                                .andExpect(status().is(401));
        }

        @Test
        @WithMockUser(username = "doe", roles = { "ADMIN" })
        void testGetAuthorAdmin() throws Exception {
                List<Author> fakedAuthors = List.of(
                                new Author().builder()
                                                .lastname("Asimov")
                                                .firstname("Isaac")
                                                .langue(Langage.DE)
                                                .build(),
                                new Author().builder()
                                                .lastname("K Dick")
                                                .firstname("Philip")
                                                .langue(Langage.ES)
                                                .build(),
                                new Author().builder()
                                                .lastname("Doe")
                                                .firstname("John")
                                                .langue(Langage.GB)
                                                .build(),
                                new Author().builder()
                                                .lastname("Dupont")
                                                .firstname("Martin")
                                                .langue(Langage.FR)
                                                .build());
                Page<Author> fakedPageAuthor = new PageImpl<>(fakedAuthors);
                when(authorService.findAll()).thenReturn(fakedPageAuthor);

                mockMvc
                                .perform(get("/author/all"))
                                .andExpect(status().is(200))
                                .andExpect(jsonPath("$").isNotEmpty())
                                .andExpect(jsonPath("$.content[0].lastname").value("Asimov"));
                verify(authorService).findAll();
        }

@Test
@WithMockUser(username = "doe", roles = { "ADMIN" })
void testGetFindByIdAuthorAdmin() throws Exception {
        CreateAuthor createAuthor = new CreateAuthor(1, "Doe", "John", Langage.DE);
        when(authorService.findById(1)).thenReturn(createAuthor);
        mockMvc
                        .perform(get("/author/1"))
                        .andExpect(status().is(200))
                        .andExpect(jsonPath("$").isNotEmpty())
                        .andExpect(jsonPath("$.id").value(1));
        verify(authorService).findById(1);
}

        @Test
        @WithMockUser(username = "doe", roles = { "ADMIN" })
        void testGetFindByIdWrongAuthorAdmin() throws Exception {
                CreateAuthor createAuthor = new CreateAuthor(1, "Asimov", "Isaac", Langage.DE);
                when(authorService.findById(1)).thenReturn(createAuthor);
                mockMvc
                                .perform(get("/author/2"))
                                .andExpect(status().is(404))
                                .andExpect(jsonPath("$").doesNotHaveJsonPath());
                verify(authorService).findById(2);
        }

        @Test
        @WithMockUser(username = "doe", roles = { "USER" })
        void testGetFindByIdAuthorUser() throws Exception {
                CreateAuthor createAuthor = new CreateAuthor(1, "Asimov", "Isaac", Langage.DE);
                when(authorService.findById(1)).thenReturn(createAuthor);
                mockMvc
                                .perform(get("/author/1"))
                                .andExpect(status().is(200))
                                .andExpect(jsonPath("$").isNotEmpty())
                                .andExpect(jsonPath("$.id").value(1));
                verify(authorService).findById(1);
        }

        @Test
        void testGetFindByIdAuthorNoUser() throws Exception {
                CreateAuthor createAuthor = new CreateAuthor(1, "Asimov", "Isaac", Langage.DE);
                when(authorService.findById(1)).thenReturn(createAuthor);
                mockMvc
                                .perform(get("/author/1"))
                                .andExpect(status().is(401));
        }

        @Test
        @WithMockUser(username = "doe", roles = { "ADMIN" })
        void testDeleteAuthorAdmin() throws Exception {
                int id = 1;
                when(authorService.remove(id)).thenReturn(true);
                mockMvc
                                .perform(delete("/author/1"))
                                .andExpect(status().isNoContent());
                verify(authorService).remove(id);
        }

        @Test
        @WithMockUser(username = "doe", roles = { "ADMIN" })
        void testDeleteWrongAuthorAdmin() throws Exception {
                int id = 1;
                when(authorService.remove(id)).thenReturn(true);
                mockMvc
                                .perform(delete("/author/2"))
                                .andExpect(status().isNotFound());
                verify(authorService).remove(2);
        }

        @Test
        @WithMockUser(username = "doe", roles = { "USER" })
        void testDeleteAuthorUser() throws Exception {
                int id = 1;
                when(authorService.remove(id)).thenReturn(true);
                mockMvc
                                .perform(delete("/author/1"))
                                .andExpect(status().is(500));
        }

        @Test
        void testDeleteAuthorNoUser() throws Exception {
                int id = 1;
                when(authorService.remove(id)).thenReturn(true);
                mockMvc
                                .perform(delete("/author/1"))
                                .andExpect(status().is(401));
        }
}