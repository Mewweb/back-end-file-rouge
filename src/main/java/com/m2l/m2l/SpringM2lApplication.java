package com.m2l.m2l;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.m2l.m2l.entities.Article;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.services.ArticleService;
import com.m2l.m2l.services.AuthorService;
import com.m2l.m2l.services.BookService;
import com.m2l.m2l.services.EditorService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class SpringM2lApplication implements ApplicationRunner{
	private BookService bookService;
	private AuthorService authorService;
	private ArticleService articleService;
	private EditorService editorService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringM2lApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		Author author = Author.builder()
				.lastname("Asimov")
				.firstname("Isaac")
				.langue("US")
				.build();
		Author author1 = Author.builder()
				.lastname("K Dick")
				.firstname("Philip")
				.langue("US")
				.build();		
		Author author2 = Author.builder()
				.lastname("Doe")
				.firstname("John")
				.langue("FR")
				.build();		
		Author author3 = Author.builder()
				.lastname("Dupont")
				.firstname("Martin")
				.langue("FR")
				.build();
		
		authorService.saveAll(List.of(author, author1, author2, author3));
		
		Editor editor = Editor.builder()
				.title("J'ai lu")
				.description("Lorem ipsum dolor sit amet consectetur adipisicing elit.")
				.date(LocalDate.parse("2018-12-07"))
				.build();
		
		Editor editor1 = Editor.builder()
				.title("Le livre de poche")
				.description("Lorem ipsum dolor sit amet consectetur adipisiing elit.")
				.date(LocalDate.parse("2019-05-24"))
				.build();
		
		Editor editor2 = Editor.builder()
				.title("10/18")
				.description("Lorem ipsum dolor sit amet consectetur adipisicing elit.")
				.date(LocalDate.parse("2020-05-28"))
				.build();
		
		editorService.saveAll(List.of(editor, editor1, editor2));
		
		Book book = Book.builder()
				.title("Les robots")
				.synopsis("Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.active(true)
				.build();
		
		Book book1 = Book.builder()
				.title("Fondations")
				.synopsis("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.active(true)
				.build();
		
		Book book2 = Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis("Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.active(true)
				.build();

		Book book3 = Book.builder()
				.title("Lorem ipsum")
				.synopsis("Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.build();
		
		bookService.saveAll(List.of(book, book1, book2, book3));
		
		for(int i = 0; i < 5; i++) {
			articleService.save(Article.builder()
					.title("Livre de poche")
					.format("100x200x75")
					.active(true)
					.editor(editor2)
					.book(book)
					.build());
			
			articleService.save(Article.builder()
					.title("Livre de poche")
					.format("10x200x25")
					.active(true)
					.editor(editor1)
					.book(book1)
					.build());
			
			articleService.save(Article.builder()
					.title("Grand format")
					.format("100x200x50")
					.active(true)
					.editor(editor1)
					.book(book1)
					.build());
			
			articleService.save(Article.builder()
					.title("Livre de poche")
					.format("50x100x25")
					.active(true)
					.editor(editor)
					.book(book2)
					.build());
			
			articleService.save(Article.builder()
					.title("Le livre de poche")
					.format("50x100x25")
					.active(true)
					.editor(editor2)
					.book(book3)
					.build());
		}
	}	
}