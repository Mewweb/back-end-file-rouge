package com.m2l.m2l.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.m2l.m2l.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{	
	@Query(""" 
			SELECT DISTINCT id, title FROM Book b
			""")
	Page<Book> selectNameBook(Pageable pageable);
	@Query(""" 
			SELECT DISTINCT id, CONCAT(title) FROM Book b WHERE
			LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	Page<Book> selectBookByTitle(@Param("keyword") String keyword, Pageable pageable);

}