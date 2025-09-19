package com.m2l.m2l.controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.entities.Book;
import com.m2l.m2l.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@CrossOrigin("http//localhost:3000/")
@AllArgsConstructor
public class BookController{
	private BookService bookService;
	
	@GetMapping
	@Secured("ROLE_USER")
	public List<Book> getBooks(){
		return bookService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id){
		var p = bookService.findById(id);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Book>(p, HttpStatus.OK);
	}
}