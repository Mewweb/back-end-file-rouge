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
			.summary("Lorem ipsum dolor sit amet consectetur adipisicing elit.")
			.reference("sqdfsqdfj5")
			.build();
		
		
		articleService.saveAll(List.of(article));
		
		bookService.save(Book.builder()
				.title("Les robots")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());

		bookService.save(Book.builder()
				.title("Fondations")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());

		bookService.save(Book.builder()
				.title("Fondations")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());

		bookService.save(Book.builder()
				.title("Fondations")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("1962-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("1965-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2021-05-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article)
				.build());
		bookService.save(Book.builder()
				.title("Les robots")
				.editor("J'ai lu")
				.stock(100)
				.style("Science-fiction")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());

		bookService.save(Book.builder()
				.title("Fondations")
				.editor("Livre de poche")
				.stock(45)
				.style("Science-fiction")
				.date(LocalDate.parse("2024-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author))
				.article(article)
				.active(true)
				.build());
		bookService.save(Book.builder()
				.title("Les androides rêvent-t'il de moutons électriques")
				.editor("10/18")
				.stock(4)
				.style("Science-fiction")
				.date(LocalDate.parse("2025-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author1))
				.article(article)
				.active(true)
				.build());
		
		bookService.save(Book.builder()
				.title("Lorem ipsum")
				.editor("J'ai lu")
				.stock(100)
				.style("Drame")
				.date(LocalDate.parse("2018-12-07"))
				.image("sdfqsd")
				.format("100x200")
				.number_isbn("qsdff")
				.authors(List.of(author2))
				.article(article)
				.build());
	}
}