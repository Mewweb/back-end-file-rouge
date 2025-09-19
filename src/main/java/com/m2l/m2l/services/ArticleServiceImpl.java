package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Article;
import com.m2l.m2l.repositories.ArticleRepository;

import jakarta.validation.Valid;

public class ArticleServiceImpl implements ArticleService{
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> findAll(){
		return articleRepository.findAll();
	}
	
	@Override
	public Article save(@Valid Article article) {
		return articleRepository.save(article);
	}
}
