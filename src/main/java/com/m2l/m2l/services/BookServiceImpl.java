package com.m2l.m2l.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.Book;
import com.m2l.m2l.repositories.BookRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
}
