package com.m2l.m2l.services;

import java.util.List;
import com.m2l.m2l.entities.Article_commande;

public interface Article_commandeService {
	List<Article_commande> findAll();
	Article_commande save(Article_commande article_commande);
}