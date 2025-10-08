package com.m2l.m2l.services;

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
		Page<Author> authors = authorRepository.test(PageRequest.of(0, 9));
		return authors;
		/*Page<Author> authors = authorRepository.test( PageRequest.of(0, 9));
		return authors;*/
	}
	
	@Override
	public List<Author> saveAll(List<Author> authors){
		return authorRepository.saveAll(authors);
	}
}
