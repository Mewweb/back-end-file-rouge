package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.m2l.m2l.dto.CreateArticle;
import com.m2l.m2l.entities.Article;

public interface ArticleService {
	List<Article> findAll();

	Boolean remove(int id);

	Page<Article> findActiveArticles(int offset, int pageSize);

	Page<Article> findAllArticles(int offset);

	Page<Article> searchArticles(String title, int offset, int pageSize);

	Article findById(int id);

	CreateArticle findByIdCreate(int id);

	Article update(CreateArticle article);

	Article save(Article article);

	Article createArticle(CreateArticle article);

	List<Article> saveAll(List<Article> articles);
}