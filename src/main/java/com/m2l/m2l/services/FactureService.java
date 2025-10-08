package com.m2l.m2l.services;

import java.util.List;
import com.m2l.m2l.entities.Facture;

public interface FactureService {
	List<Facture> findAll();
	
	Facture save(Facture facture);
}
