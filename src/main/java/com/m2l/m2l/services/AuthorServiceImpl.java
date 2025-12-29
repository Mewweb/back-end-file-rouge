package com.m2l.m2l.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.repositories.AuthorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Page<Author> findAll(){
		Page<Author> authors = authorRepository.selectNameAuthor(PageRequest.of(0, 9));
		return authors;
	}

	@Override
	public List<Author> saveAll(List<Author> authors){
		return authorRepository.saveAll(authors);
	}

	@Override
	public Page<Author> findAuthorByTitle(String keyword) {
		try {
			String urlSearch = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
			Page<Author> authors = authorRepository.selectAuthorByTitle(urlSearch, PageRequest.of(0, 9));
			return authors;
		}catch(UnsupportedEncodingException e) {
			return null;
		}
	}
}