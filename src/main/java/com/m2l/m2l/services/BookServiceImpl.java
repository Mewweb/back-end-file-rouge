package com.m2l.m2l.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.m2l.m2l.entities.Author;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.repositories.AuthorRepository;
import com.m2l.m2l.repositories.BookRepository;
import com.m2l.m2l.request.BookRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{
	@Autowired
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
	
	/*@Override
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
	}*/
	
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
	
	public ResponseEntity<Book> createBook(@RequestBody BookRequest request) {
		System.out.println(request.getAuthorsId());
		List<Author> authors = authorRepository.findAllById(request.getAuthorsId());
		if(authors.isEmpty()) {
			throw new RuntimeException("No authors found with provided IDs");
		}
		Book book = Book.builder()
				.title(request.getTitle())
				.synopsis(request.getSynopsis())
				.stock(request.getStock())
				.style(request.getStyle())
				.image(request.getImage())
				.date(request.getDate())
				.authors(authors)
				.build();
		Book savedBook = bookRepository.save(book);
		return ResponseEntity.ok(savedBook);
	}	
	
	@Override
	public List<Book> saveAll(List<Book> books){
		return bookRepository.saveAll(books);
	}
}
