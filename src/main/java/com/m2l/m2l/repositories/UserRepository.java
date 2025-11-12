package com.m2l.m2l.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.m2l.m2l.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
}
