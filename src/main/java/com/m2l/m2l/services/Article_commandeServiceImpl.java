package com.m2l.m2l.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m2l.m2l.entities.Article_commande;
import com.m2l.m2l.repositories.Article_commandeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class Article_commandeServiceImpl implements Article_commandeService{
	@Autowired
	private Article_commandeRepository article_commandeRepository;
	
	@Override
	public List<Article_commande> findAll(){
		return article_commandeRepository.findAll();
	}
	
	@Override
	public Article_commande save(@Valid Article_commande article_commande) {
		return article_commandeRepository.save(article_commande);
	}
}
