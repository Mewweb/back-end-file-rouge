package com.m2l.m2l;

import java.awt.print.Printable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.m2l.m2l.configuration.RsaKeyProperties;
import com.m2l.m2l.entities.Article;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.entities.CartItem;
import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.entities.User;
import com.m2l.m2l.enums.Langage;
import com.m2l.m2l.enums.Role;
import com.m2l.m2l.repositories.UserRepository;
import com.m2l.m2l.services.ArticleService;
import com.m2l.m2l.services.AuthorService;
import com.m2l.m2l.services.BookService;
import com.m2l.m2l.services.CartItemService;
import com.m2l.m2l.services.EditorService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
@EnableConfigurationProperties(RsaKeyProperties.class)
public class M2lApplication implements ApplicationRunner {

	private BookService bookService;
	private AuthorService authorService;
	private ArticleService articleService;
	private EditorService editorService;
	private CartItemService cartItemService;
	private UserRepository userRepository;
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(M2lApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Author author = Author.builder()
				.lastname("Asimov")
				.firstname("Isaac")
				.langue(Langage.DE)
				.build();
		Author author1 = Author.builder()
				.lastname("K Dick")
				.firstname("Philip")
				.langue(Langage.ES)
				.build();
		Author author2 = Author.builder()
				.lastname("Doe")
				.firstname("John")
				.langue(Langage.GB)
				.build();
		Author author3 = Author.builder()
				.lastname("Dupont")
				.firstname("Martin")
				.langue(Langage.FR)
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
				.synopsis(
						"Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("C:\\Users\\mewen\\OneDrive\\Images\\Screenshots\\de.png")
				.authors(List.of(author, author1))
				.build();

		Book book1 = Book.builder()
				.title("Fondations")
				.synopsis(
						"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("C:\\Users\\mewen\\OneDrive\\Images\\Screenshots\\de.png")
				.authors(List.of(author))
				.build();

		Book book2 = Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis(
						"Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("C:\\Users\\mewen\\OneDrive\\Images\\Screenshots\\de.png")
				.authors(List.of(author1))
				.build();

		Book book3 = Book.builder()
				.title("Lorem ipsum")
				.synopsis(
						"Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("C:\\Users\\mewen\\OneDrive\\Images\\Screenshots\\de.png")
				.authors(List.of(author2))
				.build();

		bookService.saveAll(List.of(book, book1, book2, book3));
		Article article1 = Article.builder()
				.title("Grand format")
				.width(200)
				.height(450)
				.thickness(50)
				.active(true)
				.stock(100)
				.editor(editor2)
				.book(book)
				.price(15)
				.number_isbn("1234567890123")
				.build();

		Article article2 = Article.builder()
				.title("Format numérique")
				.width(200)
				.height(450)
				.thickness(50)
				.active(true)
				.stock(25)
				.editor(editor1)
				.book(book3)
				.price(13)
				.number_isbn("1234567890123")
				.build();

		for (int i = 0; i < 5; i++) {
			articleService.save(Article.builder()
					.title("Livre de poche")
					.width(200)
					.height(450)
					.thickness(50)
					.active(true)
					.stock(145)
					.editor(editor2)
					.book(book)
					.price(18)
					.number_isbn("1234567890123")
					.build());

			articleService.save(Article.builder()
					.title("Livre de poche")
					.width(200)
					.height(450)
					.thickness(50)
					.active(true)
					.stock(42)
					.editor(editor1)
					.book(book1)
					.price(8)
					.number_isbn("1234567890123")
					.build());

			articleService.save(Article.builder()
					.title("Grand format")
					.width(200)
					.height(450)
					.thickness(50)
					.active(true)
					.stock(48)
					.editor(editor1)
					.price(12)
					.book(book1)
					.number_isbn("1234567890123")
					.build());

			articleService.save(Article.builder()
					.title("Livre de poche")
					.width(200)
					.height(450)
					.thickness(50)
					.active(true)
					.stock(75)
					.editor(editor)
					.book(book2)
					.price(12)
					.number_isbn("1234567890123")
					.build());

			articleService.save(Article.builder()
					.title("Le livre de poche")
					.width(200)
					.height(450)
					.thickness(50)
					.active(true)
					.stock(78)
					.editor(editor2)
					.book(book3)
					.price(21)
					.number_isbn("1234567890123")
					.build());
		}

		User user = User.builder()
				.lastname("Doe")
				.firstname("John")
				.phone_number("06 05 04 03 02")
				.email("test@test.fr")
				.password(encoder.encode("test@test.fr"))
				.billing_address("66 rue des avenues")
				.delivery_address("44 rue des avenues")
				.role(Role.ADMIN)
				.build();

		CartItem cartItem = CartItem.builder()
				.user(user)
				.article(article1)
				.quantity(2)
				.build();

		CartItem cartItem1 = CartItem.builder()
				.user(user)
				.article(article2)
				.quantity(1)
				.build();

		articleService.save(article1);
		articleService.save(article2);

		userRepository.save(user);

		cartItemService.save(cartItem);
		cartItemService.save(cartItem1);
	}
}