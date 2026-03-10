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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.dto.CreateArticle;
import com.m2l.m2l.entities.Article;
import com.m2l.m2l.services.ArticleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
	private ArticleService articleService;
	
	@GetMapping("/{offset}/{pageSize}")
	public ResponseEntity<Page<Article>> findActiveAll(@PathVariable int offset, @PathVariable int pageSize){
		Page<Article> allArticles = articleService.findActiveArticles(offset, pageSize);
		return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
	}
	
	@GetMapping("/all/{offset}")
	public ResponseEntity<Page<Article>> findAll(@PathVariable int offset){
		Page<Article> allArticles = articleService.findAllArticles(offset);
		return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
	}
	
	@GetMapping("/{offset}/{pageSize}/{title}")
	public ResponseEntity<Page<Article>> searchBooks(@PathVariable String title, @PathVariable int offset, @PathVariable int pageSize){
		Page<Article> allArticles = articleService.searchArticles(title.replaceAll("-", "/"), offset, pageSize);
		return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Void> deleteArticle(@PathVariable int id){
		var b = articleService.remove(id);
		if(b == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Article> updateArticle(@RequestBody CreateArticle article, @PathVariable int id){
		if(id != article.getId()) {
			return ResponseEntity.badRequest().build();
		}
		var b = articleService.update(article);
		if(b == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Article>(b, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/lastArticle")
	public ResponseEntity<Page<Article>> findThreeLastBook(){
		Page<Article> allArticles = articleService.findActiveArticles(0, 3);
		return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Article> getArticle(@PathVariable int id){
		var p = articleService.findById(id);
		if(p == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Article>(p, HttpStatus.OK);
	}
	
	@GetMapping("/admin/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<CreateArticle> getAdminArticle(@PathVariable int id){
		var a = articleService.findByIdCreate(id);
		if(a == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<CreateArticle>(a, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Article> addArticle(@RequestBody CreateArticle article) {
		var a = articleService.createArticle(article);
		if(a == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Article>(a, HttpStatus.CREATED);
	}
}
