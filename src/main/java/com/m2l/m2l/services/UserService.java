package com.m2l.m2l.services;

import java.util.List;
import com.m2l.m2l.entities.User;

public interface UserService {
	List<User> findAll();
	User save(User user);
}
