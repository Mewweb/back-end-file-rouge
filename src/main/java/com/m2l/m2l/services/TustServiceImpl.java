package com.m2l.m2l.services;

import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.Tust;
import com.m2l.m2l.repositories.TustRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TustServiceImpl implements TustService{
	private TustRepository tustRepository;
	
	@Override
	public Tust save(@Valid Tust tust) {
		return tustRepository.save(tust);
	}
}
