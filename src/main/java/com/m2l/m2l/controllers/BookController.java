package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.m2l.m2l.dto.CreateBook;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.services.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController{
	private BookService bookService;
	@GetMapping("/all")
	public ResponseEntity<Page<Book>> findAll(){
		Page<Book> allBooks = bookService.findAll();
		return new ResponseEntity<Page<Book>>(allBooks,HttpStatus.OK);
	}
	
	@GetMapping("/all/{offset}")
	public ResponseEntity<Page<Book>> findAllWithOffset(@PathVariable int offset){
		Page<Book> allBooks = bookService.findBook(offset, 9);
		return new ResponseEntity<Page<Book>>(allBooks, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CreateBook> getBook(@PathVariable int id){
		var b = bookService.findByIdCreate(id);
		if(b == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<CreateBook>(b, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Void> deleteBook(@PathVariable int id){
		var b = bookService.remove(id);
		if(b == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@Secured({"ROLE_ADMIN"})
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody CreateBook book, @PathVariable int id){
		if(id != book.getId()) {
			return ResponseEntity.badRequest().build();
		}
		var b = bookService.update(book);
		if(b == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Book>(b, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<Page<Book>> findByTitle(@PathVariable String keyword){
		Page<Book> allBooks = bookService.findBookByTitle(keyword);
		return new ResponseEntity<Page<Book>>(allBooks, HttpStatus.OK);			
	}
	
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addBook(@RequestPart CreateBook book,@RequestPart MultipartFile imageFile) {
			try {
			Book createBook = bookService.createBook(book, imageFile);
			return new ResponseEntity<>(createBook, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("fjfjfj");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}