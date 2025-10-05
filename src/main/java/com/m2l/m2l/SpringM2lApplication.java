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
import com.m2l.m2l.services.ArticleService;
import com.m2l.m2l.services.AuthorService;
import com.m2l.m2l.services.BookService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class SpringM2lApplication implements ApplicationRunner{
	private BookService bookService;
	private AuthorService authorService;
	private ArticleService articleService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringM2lApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		Author author = Author.builder()
				.lastname("Asimov")
				.firstname("Asimov")
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
		
		
		Article article = Article.builder()
				.title("Livre de poche")
				.format("11X17.50X1.30")
				.build();
		
		Article article2 = Article.builder()
				.title("Grand format")
				.format("22X100X3.2")
				.build();		
		
		articleService.saveAll(List.of(article,article2));
		
		bookService.save(Book.builder()
				.title("Les robots")
				.synopsis("Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());

		bookService.save(Book.builder()
				.title("Fondations")
				.synopsis("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis("Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.synopsis("Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")

				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article2)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.synopsis("Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article2)
				.active(true)
				.build());

		bookService.save(Book.builder()
				.title("Fondations")
				.synopsis("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article2)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis("Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.synopsis("Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.synopsis("Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article2)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Fondations")
				.synopsis("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis("Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.synopsis("Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article2)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.synopsis("Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article2)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Fondations")
				.synopsis("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis("Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article2)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.synopsis("Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article2)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.synopsis("Lorem ipsum dolor sit amet consectetur adipisicing elit. Elit adipisicing consectetur amet sit dolor ipsum lorem.")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article2)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Fondations")
				.synopsis("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi erchitecto beatae vitae dicta sunt explicabo.")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.synopsis("Neque porro quisuqam est, qui dolorem ipsum quia dolor sit emet consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article2)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.synopsis("Sed do uiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article2)
				.build());
	}	
}