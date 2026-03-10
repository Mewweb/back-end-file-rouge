package com.m2l.m2l.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.m2l.m2l.dto.CreateBook;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.repositories.AuthorRepository;
import com.m2l.m2l.repositories.BookRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
    private Environment env;
	
	@Override
	public Page<Book> findAll(){
		Page<Book> books = bookRepository.selectNameBook(PageRequest.of(0, 9));
		return books;
	}
	
	@Override
	public Page<Book> findBookByTitle(String keyword) {
		try {
			String urlSearch = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
			Page<Book> books = bookRepository.selectBookByTitle(urlSearch, PageRequest.of(0, 9));
			return books;
		}catch(UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/*@Autowired
    public Path saveImage(InputStream data, String originalFileName) throws IOException {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        Path p;
        do {
            p = Paths.get(env.getProperty("images.path") + UUID.randomUUID() + extension);
        } while (Files.exists(p));
        Files.copy(data, p);
        return p.getFileName();
    }*/

	@Override
	public Boolean remove(int id) {
		Book book = bookRepository.findById(id).orElse(null);
		if(book != null) {
			book.getAuthors().clear();
			bookRepository.delete(book);
			return true;
		}
		return false;
	}
	
	@Override
	public Page<Book> findBook(int offset, int pageSize){
		Page<Book> books = bookRepository.findAll(PageRequest.of(offset,pageSize));
		return books;
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
	
	@Override
	public Book createBook(@RequestBody CreateBook request, MultipartFile imageFile) throws IOException {
		String extension = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
		Path p ;
		do {
			p = Paths.get(env.getProperty("images.path") + UUID.randomUUID() + extension);
		} while(Files.exists(p));
		Files.copy(imageFile.getInputStream(), p);
		List<Author> authors = authorRepository.findAllById(request.getAuthors());
		if(authors.isEmpty()) {
			throw new RuntimeException("No authors found with provided IDs");
		}
		System.out.println(imageFile.getResource().toString());
		Book book = Book.builder()
				.title(request.getTitle())
				.synopsis(request.getSynopsis())
				.style(request.getStyle())
				.image(env.getProperty("images.path").concat(p.getFileName().toString()))
				.date(request.getDate())
				.authors(authors)
				.build();
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}
	
	@Override
	public List<Book> saveAll(List<Book> books){
		return bookRepository.saveAll(books);
	}

	@Override
	public Book update(@Valid CreateBook request) {
		try {
			List<Author> authors = authorRepository.findAllById(request.getAuthors());
			Book book = Book.builder()
					.id(request.getId())
					.title(request.getTitle())
					.synopsis(request.getSynopsis())
					.style(request.getStyle())
					.image(request.getImage())
					.date(request.getDate())
					.authors(authors)
					.build();
			return bookRepository.save(book);
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public CreateBook findByIdCreate(int id) {
		Book book = bookRepository.findById(id).orElse(null);
		CreateBook createBook = new CreateBook();
		createBook.setId(book.getId());
		createBook.setSynopsis(book.getSynopsis());
		createBook.setTitle(book.getTitle());
		createBook.setStyle(book.getStyle());
		createBook.setImage(book.getImage());
		createBook.setDate(book.getDate());
		List<Integer> allId = new ArrayList<Integer>();
		for(int i = 0; i < book.getAuthors().size(); i++) {
			allId.add(book.getAuthors().get(i).getId());
		}
		createBook.setAuthors(allId);
		return createBook;
	}
}