package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Book;

public interface BookService {
	List<Book> findAll();
	
	Book findById(int id);
	
	Book save(Book book);
}
