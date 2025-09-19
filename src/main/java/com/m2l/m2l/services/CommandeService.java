package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Commande;

public interface CommandeService {
	List<Commande> findAll();
	
	Commande save(Commande commande);
}
