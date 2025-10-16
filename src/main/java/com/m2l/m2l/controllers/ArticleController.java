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

import com.m2l.m2l.entities.Article;
import com.m2l.m2l.services.ArticleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
	private ArticleService articleService;
	
	@GetMapping("/{offset}/{pageSize}")
	public ResponseEntity<Page<Article>> findAll(@PathVariable int offset, @PathVariable int pageSize){
		Page<Article> allArticles = articleService.findActiveArticles(offset, pageSize);
		return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
	}
	
	@GetMapping("/{offset}/{pageSize}/{title}")
	public ResponseEntity<Page<Article>> searchBooks(@PathVariable String title, @PathVariable int offset, @PathVariable int pageSize){
		Page<Article> allArticles = articleService.searchArticles(title.replaceAll("-", "/"), offset, pageSize);
		return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Article addArticle(@RequestBody Article article) {
		return articleService.save(article);
	}
}
