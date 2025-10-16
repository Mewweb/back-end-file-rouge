package com.m2l.m2l.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.m2l.m2l.entities.Test;
import com.m2l.m2l.entities.Tust;
import com.m2l.m2l.repositories.TestRepository;
import com.m2l.m2l.repositories.TustRepository;
import com.m2l.m2l.request.TestRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TestServiceImpl implements TestService {
	private TestRepository testRepository;
	private TustRepository tustRepository;
	
	@Override
	public Test save(@RequestBody TestRequest request) {
		
		Tust tust = tustRepository.findById(request.getTustId())
				.orElseThrow(() -> new RuntimeException("Tust Not Found"));
		
		Test test = Test.builder()
				.title(request.getTitle())
				.tust(tust)
				.build();
		return testRepository.save(test);
		/*if(test.getTust() != null) {
			
			tustRepository.save(test.getTust());
		}
		return testRepository.save(test);*/
	}
}
