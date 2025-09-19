package com.m2l.m2l;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.m2l.m2l.services.AuthorService;
import com.m2l.m2l.services.BookService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class M2lApplication implements ApplicationRunner{
	private BookService bookService;
	private AuthorService authorService;
	
	public static void main(String[] args) {
		SpringApplication.run(M2lApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		/*var author = new Author("John","Doe","FR");
		var article = new Article("Le livre de poche", "Lorem ipsum dolor sit amet consectetur adipisicing elit.", "sdlfksqdfksqmsfsqfqsfqsfsqdfqsf");
		
		authorService.saveAll(List.of(author));
		bookService.save(new Book("Les robots", "J'ai lu",100,"Science-fiction","fjfj","100x200","dfqsdkfqsmlf",List.of(author), article));*/
	}
}