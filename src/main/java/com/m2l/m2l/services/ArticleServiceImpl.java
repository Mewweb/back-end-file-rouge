package com.m2l.m2l.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<Article> findActiveArticles(int offset, int pageSize){
		Page<Article> articles = articleRepository.findByActiveOrderByAddDateDesc(true, PageRequest.of(offset, pageSize));
		return articles;
	}
	
	@Override
	public Page<Article> searchArticles(String title, int offset, int pageSize){
		try {
			String urlSearch = URLDecoder.decode(title, StandardCharsets.UTF_8.name());
			Page<Article> articles = articleRepository.searchActiveAndBooksByTitleOrEditorOrStyleOrAuthors(true, urlSearch, PageRequest.of(offset, pageSize));
			return articles;
		}catch(UnsupportedEncodingException e) {
			return null;
		}
	}
	
	@Override
	public Article findById(int id) {
		return articleRepository.findById(id).orElse(null);
	}
	
	@Override
	public Article save(Article article) {
		return articleRepository.save(article);
	}
	
	@Override
	public List<Article> saveAll(List<Article> articles){
		return articleRepository.saveAll(articles);
	}
}