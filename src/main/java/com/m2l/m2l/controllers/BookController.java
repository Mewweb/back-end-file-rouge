package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.dto.CreateBook;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController{
	private BookService bookService;
	
	@GetMapping("/admin/{offset}")
	public ResponseEntity<Page<Book>> findAll(@PathVariable int offset){
		Page<Book> allBooks = bookService.findActiveBooks(offset, 9);
		return new ResponseEntity<Page<Book>>(allBooks, HttpStatus.OK);
	}
	
	@GetMapping("/{keyword}")
	public ResponseEntity<Page<Book>> findByTitle(@PathVariable String keyword){
		Page<Book> allBooks = bookService.findBookByTitle(keyword);
		return new ResponseEntity<Page<Book>>(allBooks, HttpStatus.OK);			
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> addBook(@RequestBody CreateBook book) {
		return bookService.createBook(book);
	}
}