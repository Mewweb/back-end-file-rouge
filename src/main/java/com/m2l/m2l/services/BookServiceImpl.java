package com.m2l.m2l.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.Book;
import com.m2l.m2l.repositories.AuthorRepository;
import com.m2l.m2l.repositories.BookRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{
	private BookRepository bookRepository;
	private AuthorRepository authorRepository;
	
	@Override
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	@Override
	public Page<Book> findActiveBooks(int offset, int pageSize){
		Page<Book> books = bookRepository.findByActiveOrderByAddDateDesc(true, PageRequest.of(offset, pageSize));
		return books;
	}
	
	@Override
	public Page<Book> searchBooks(String title, int offset, int pageSize){
		try {
			String urlSearch = URLDecoder.decode(title, StandardCharsets.UTF_8.name());
			System.out.println(urlSearch);
			Page<Book> books = bookRepository.searchActiveBooksByTitleOrEditorOrStyleOrAuthors(true, urlSearch, PageRequest.of(offset, pageSize));
			return books;
		}catch(UnsupportedEncodingException e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public Book findById(int id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	@Override
	public Book save(@Valid Book book) {
		if(book.getAuthors() != null) {
			authorRepository.saveAll(book.getAuthors());
		}
		return bookRepository.save(book);
	}
	
}
