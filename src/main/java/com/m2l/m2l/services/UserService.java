package com.m2l.m2l.services;

import java.util.List;
import com.m2l.m2l.dto.UserDataDto;
import com.m2l.m2l.entities.User;

public interface UserService {
	public User checkUser(String email, String password);
	List<User> findAll();
	User save(User user);
	UserDataDto getUser(String email);
	//List<ArticleUserDto> findAllArticlesByUser(String email);
}