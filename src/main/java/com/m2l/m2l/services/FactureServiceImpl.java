package com.m2l.m2l.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m2l.m2l.entities.Facture;
import com.m2l.m2l.repositories.FactureRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FactureServiceImpl implements FactureService{
	@Autowired
	private FactureRepository factureRepository;
	
	@Override
	public List<Facture> findAll(){
		return factureRepository.findAll();
	}
	
	@Override
	public Facture save(@Valid Facture facture) {
		return factureRepository.save(facture);
	}
}
