package com.m2l.m2l.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.Article;
import com.m2l.m2l.repositories.ArticleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> findAll(){
		return articleRepository.findAll();
	}
	
	@Override
	public List<Article> saveAll(List<Article> articles){
		return articleRepository.saveAll(articles);
	}
}
