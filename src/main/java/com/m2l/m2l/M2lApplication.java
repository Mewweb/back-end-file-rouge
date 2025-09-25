package com.m2l.m2l;

import java.awt.print.Printable;
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
public class M2lApplication implements ApplicationRunner{
	private BookService bookService;
	private AuthorService authorService;
	private ArticleService articleService;
	
	public static void main(String[] args) {
		SpringApplication.run(M2lApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		
		var author = new Author("Isaac","Asimov","US");
		var author1 = new Author("Philip K.", "Dick","US");
		authorService.saveAll(List.of(author, author1));
		
		var article = new Article("Le livre de poche","Lorem ipsum dolor sit amet consectetur adipisicing elit.", "klqsfjksfqsljdfqsmf");
		
		articleService.saveAll(List.of(article));
		
		System.out.println(LocalDate.of(2021,1,1));

		bookService.save(new Book("Les robots","J'ai lu",100,"Science-fiction",LocalDate.parse("2018-12-07"),"qsdfqsdf","100x200","sdùmfkl",List.of(author), article));
		bookService.save(new Book("Fondations","J'ai lu",205,"Science-fiction", LocalDate.parse("1982-05-06"),"sdfdsf","100x200","sdfdsf", List.of(author), article));
		bookService.save(new Book("Les androïdes rêvent-t'il des moutons électriques ?", "J'ai lu",50,"Science-fiction",LocalDate.parse("1965-01-02"), "sdjfsdf","100x200","dsfsqdf",List.of(author1), article));
		
	}
}