package com.m2l.m2l.services;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import com.m2l.m2l.dto.CreateBook;
import com.m2l.m2l.entities.Book;

public interface BookService {
	Page<Book> findAll();

	Boolean remove(int id);

	Page<Book> findBook(int offset, int pageSize);

	Page<Book> findBookByTitle(String keyword);

	Book findById(int id);

	CreateBook findByIdCreate(int id);

	Book save(Book book);

	Book update(CreateBook book, MultipartFile ImageFile);

	Book createBook(CreateBook book, MultipartFile imageFile) throws IOException;

	List<Book> saveAll(List<Book> books);
}