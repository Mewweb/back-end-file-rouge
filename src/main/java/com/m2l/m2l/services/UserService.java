package com.m2l.m2l.services;

import java.util.List;
import com.m2l.m2l.dto.PasswordUser;
import com.m2l.m2l.dto.UserDataDto;
import com.m2l.m2l.entities.User;

public interface UserService {
	public User checkUser(String email, String password);

	List<User> findAll();

	User save(User user);

	User update(UserDataDto user);

	UserDataDto getUser(String email);

	User updatePassword(PasswordUser passwords);
}