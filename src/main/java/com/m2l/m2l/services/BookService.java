package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.m2l.m2l.dto.CreateBook;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.request.BookRequest;

public interface BookService {
	List<Book> findAll();
	Page<Book> findActiveBooks(int offset, int pageSize);
	//Page<Book> searchBooks(String title, int offset, int pageSize);
	Book findById(int id);
	Book save(Book book);
	ResponseEntity<Book> createBook(CreateBook book);	
	List<Book> saveAll(List<Book> books);
	
}