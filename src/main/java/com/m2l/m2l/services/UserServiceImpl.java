package com.m2l.m2l.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.User;
import com.m2l.m2l.repositories.BookRepository;
import com.m2l.m2l.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	private BookRepository bookRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User save(@Valid User user) {
		/*if(user.getArticles() != null) {
			articleRepository.saveAll(user.getArticles());
		}*/
		if(user.getBooks() != null) {
			bookRepository.saveAll(user.getBooks());
		}
		return userRepository.save(user);
	}
}
