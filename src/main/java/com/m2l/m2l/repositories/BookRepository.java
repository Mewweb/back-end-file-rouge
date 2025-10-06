package com.m2l.m2l.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.m2l.m2l.entities.Book;


public interface BookRepository extends JpaRepository<Book, Integer>{	
	Page<Book> findByActiveOrderByAddDateDesc(Boolean active, Pageable pageable);
	/*@Query("""
			SELECT b FROM Book b JOIN b.author a
			WHERE b.active = :active
			AND (LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(b.editor) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(a.lastname) LIKE LOWER(CONCAT('%', :keyword, '%'))
			)
			""")
		Page<Book> searchActiveBooksByTitleOrEditorOrStyleOrAuthors(
		    @Param("active") Boolean active,
		    @Param("keyword") String keyword,
		    Pageable pageable);*/
	@Query("""
			SELECT DISTINCT b FROM Book b JOIN b.authors a
			WHERE b.active = :active
			AND (LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(a.lastname) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(a.firstname) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(b.editor) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(b.style) LIKE LOWER(CONCAT('%', :keyword,'%'))
			) 
			""")
		Page<Book> searchActiveBooksByTitleOrEditorOrStyleOrAuthors(
		    @Param("active") Boolean active,
		    @Param("keyword") String keyword,
		    Pageable pageable);
	
	
	//Page<Book> findAllByActiveAndTitleContainingIgnoreCase(Boolean active, String title, Pageable pageable);
}
