package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.m2l.m2l.dto.CreateAuthor;
import com.m2l.m2l.entities.Author;

public interface AuthorService {
	Page<Author> findAll();

	Boolean remove(int id);

	CreateAuthor findById(int id);

	Author update(Author author);

	Author save(CreateAuthor author);

	List<Author> saveAll(List<Author> authors);

	Page<Author> findAllWithOffset(int offset);

	Page<Author> findAuthorByTitle(String keyword);

	Author createAuthor(CreateAuthor author);
}