package com.m2l.m2l.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.m2l.m2l.dto.PasswordUser;
import com.m2l.m2l.dto.UserDataDto;
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
	
	@Override
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
	
	@Override
	public UserDataDto getUser(String email) {
		User user = userRepository.findByEmail(email);
		return new UserDataDto(user.getId(),user.getLastname(),user.getFirstname(),user.getPhone_number(),user.getEmail(),user.getBilling_address(),user.getDelivery_address());
	}
	
	@Override
	public User update(@Valid UserDataDto user){
		try {
			Optional<User> userOptional = userRepository.findById(user.getId());
			userOptional.get().setFirstname(user.getFirstname());
			userOptional.get().setLastname(user.getLastname());
			userOptional.get().setPhone_number(user.getPhone_number());
			userOptional.get().setEmail(user.getEmail());
			userOptional.get().setBilling_address(user.getBilling_address());
			userOptional.get().setDelivery_address(user.getDelivery_address());
			return userRepository.save(userOptional.get());
		}catch(Exception e) {
			return null;
		}
	}
	
	public User updatePassword(PasswordUser passwords) {
		try {
			Optional<User> userOptional = userRepository.findById(passwords.getId());
			if(encoder.matches(passwords.getOldPassword(), userOptional.get().getPassword())) {
				userOptional.get().setPassword(encoder.encode(passwords.getNewPassword()));
				return userRepository.save(userOptional.get());
			}
			return null;
			
		}catch(Exception e) {
			return null;
		}
	}
	
	
}