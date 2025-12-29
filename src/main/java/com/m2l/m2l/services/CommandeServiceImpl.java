package com.m2l.m2l.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m2l.m2l.entities.Commande;
import com.m2l.m2l.repositories.CommandeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommandeServiceImpl implements CommandeService{
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Override
	public List<Commande> findAll(){
		return commandeRepository.findAll();
	}
	
	@Override
	public Commande save(@Valid Commande commande) {
		return commandeRepository.save(commande);
	}
}