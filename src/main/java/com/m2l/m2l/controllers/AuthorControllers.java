package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.services.AuthorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorControllers {
	private AuthorService authorService;
	
	@GetMapping("/all")
	public ResponseEntity<Page<Author>> findAll(){
		Page<Author> allAuthors = authorService.findAll();
		return new ResponseEntity<Page<Author>>(allAuthors, HttpStatus.OK);
	}
	
	@GetMapping("/{keyword}")
	public ResponseEntity<Page<Author>> findByTitle(@PathVariable String keyword){
		Page<Author> allAuthors = authorService.findAuthorByTitle(keyword);
		return new ResponseEntity<Page<Author>>(allAuthors, HttpStatus.OK);			
	}
}
