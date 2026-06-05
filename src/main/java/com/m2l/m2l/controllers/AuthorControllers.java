package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.m2l.m2l.dto.CreateAuthor;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorControllers {
	private AuthorService authorService;

	@GetMapping("/all")
	public ResponseEntity<Page<Author>> findAll() {
		Page<Author> allAuthors = authorService.findAll();
		return new ResponseEntity<Page<Author>>(allAuthors, HttpStatus.OK);
	}

	@GetMapping("/all/{offset}")
	public ResponseEntity<Page<Author>> findAllWithOffset(@PathVariable int offset) {
		Page<Author> allAuthors = authorService.findAllWithOffset(offset);
		return new ResponseEntity<Page<Author>>(allAuthors, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CreateAuthor> getAuthor(@PathVariable int id) {
		var a = authorService.findById(id);
		if (a == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<CreateAuthor>(a, HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Author> postMethodName(@RequestBody CreateAuthor author) {
		var a = authorService.save(author);
		if(a == null){
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Author>(a, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
		var a = authorService.remove(id);
		if (a == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<Page<Author>> findByTitle(@PathVariable String keyword) {
		Page<Author> allAuthors = authorService.findAuthorByTitle(keyword);
		return new ResponseEntity<Page<Author>>(allAuthors, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable int id) {
		if (id != author.getId()) {
			return ResponseEntity.badRequest().build();
		}
		var a = authorService.update(author);
		if (a == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Author>(a, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> addAuthor(@RequestBody CreateAuthor author) {
		try {
			Author createAuthor = authorService.createAuthor(author);
			return new ResponseEntity<>(createAuthor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
