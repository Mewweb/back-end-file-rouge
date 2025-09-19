package com.m2l.m2l.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.Facture;
import com.m2l.m2l.repositories.FactureRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FactureServiceImpl implements FactureService{
	private FactureRepository factureRepository;
	
	public List<Facture> findAll(){
		return factureRepository.findAll();
	}
	
	public Facture save(@Valid Facture facture) {
		return factureRepository.save(facture);
	}
}
