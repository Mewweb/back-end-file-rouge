package com.m2l.m2l.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.m2l.m2l.entities.Book;


public interface BookRepository extends JpaRepository<Book, Integer>{
	Page<Book> findByActive(Boolean active, Pageable pageable);
	
	Page<Book> findByActiveOrderByDateDesc(Boolean active, Pageable pageable);
}
