package com.m2l.m2l.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.m2l.m2l.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	Page<Article> findByActiveOrderByAddDateDesc(Boolean active, Pageable pageable);
	
	@Query(""" 
			SELECT DISTINCT a FROM Article a 
			JOIN a.book b 
			JOIN a.editor e 
			JOIN b.authors u 
			WHERE a.active = :active
			AND (LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(u.firstname) LIKE LOWER(CONCAT('%', :keyword,'%'))
			OR LOWER(b.style) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			)""")
	Page<Article> searchActiveAndBooksByTitleOrEditorOrStyleOrAuthors(
	@Param("active") Boolean active,
	@Param("keyword") String keyword,
	Pageable pageable);
}
