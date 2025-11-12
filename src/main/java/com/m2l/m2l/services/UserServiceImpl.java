package com.m2l.m2l.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.User;
import com.m2l.m2l.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	private PasswordEncoder encoder;
	
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User checkUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new BadCredentialsException("Identifiant invalides");
		}
		if(!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Identifiants invalides");
		}
		return user;
	}
	
	@Override
	public User save(@Valid User user) {
		return userRepository.save(user);
	}
}
