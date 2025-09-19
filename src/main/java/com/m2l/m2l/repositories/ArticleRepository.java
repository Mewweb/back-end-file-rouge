package com.m2l.m2l.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2l.m2l.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{

}
