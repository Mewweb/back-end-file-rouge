package com.m2l.m2l.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.m2l.m2l.entities.Facture;

public interface FactureRepository extends JpaRepository<Facture, Integer> {}