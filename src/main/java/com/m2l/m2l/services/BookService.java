package com.m2l.m2l.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.m2l.m2l.entities.Book;

public interface BookService {
	List<Book> findAll();
	
	Page<Book> findActiveBooks(int offset, int pageSize);
	
	Book findById(int id);
	
	Book save(Book book);
}
