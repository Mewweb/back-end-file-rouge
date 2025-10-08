package com.m2l.m2l.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.m2l.m2l.entities.Article;

public interface ArticleService {
	List<Article> findAll();
	
	Page<Article> findActiveArticles(int offset, int pageSize);
	
	Page<Article> searchArticles(String title, int offset, int pageSize);
	
	Article findById(int id);
	
	Article save(Article article);
	
	List<Article> saveAll(List<Article> articles);
}
