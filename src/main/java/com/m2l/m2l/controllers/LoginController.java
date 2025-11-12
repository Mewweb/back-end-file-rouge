package com.m2l.m2l.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@GetMapping("/user")
		public String getUser() {
			return "Welcome, User";
		}
		
		@GetMapping("/admin")
		public String getAdmin() {
			return "Welcolme, Admin";
		}
}
