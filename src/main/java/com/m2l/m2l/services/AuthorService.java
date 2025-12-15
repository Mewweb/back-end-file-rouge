package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.m2l.m2l.entities.Author;

public interface AuthorService {
	Page<Author> findAll();
	List<Author> saveAll(List<Author> authors);	
	Page<Author> findAuthorByTitle(String keyword);
}