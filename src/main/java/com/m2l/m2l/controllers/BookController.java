package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.entities.Book;
import com.m2l.m2l.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")

@AllArgsConstructor
public class BookController {
	private BookService bookService;
	
	@GetMapping("/{offset}/{pageSize}")
	public ResponseEntity<Page<Book>> findAll(@PathVariable int offset, @PathVariable int pageSize){
		Page<Book> allBooks = bookService.findBookActive(offset, pageSize);
		return new ResponseEntity<Page<Book>>(allBooks, HttpStatus.OK);
	}
	
	
	@GetMapping("/lastBook")
	public ResponseEntity<Page<Book>> findThreeLastBook(){
		Page<Book> allBooks = bookService.findThreeLastBook();
		return new ResponseEntity<Page<Book>>(allBooks, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id){
		var p = bookService.findById(id);
		if(p == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Book>(p, HttpStatus.OK);
	}
}
