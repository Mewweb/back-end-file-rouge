package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Article;

public interface ArticleService {
	List<Article> findAll();
	
	Article save(Article article);
}
