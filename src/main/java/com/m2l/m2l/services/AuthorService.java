package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Author;

public interface AuthorService {
	List<Author> findAll();
	
	List<Author> saveAll(List<Author> authors);
}
