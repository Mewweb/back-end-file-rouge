package com.m2l.m2l.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.entities.Test;
import com.m2l.m2l.request.TestRequest;
import com.m2l.m2l.services.TestService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
	private TestService testService;
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Test addTest(@RequestBody TestRequest test) {
		return testService.save(test);
	}
}
