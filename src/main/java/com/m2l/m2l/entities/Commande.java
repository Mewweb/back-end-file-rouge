package com.m2l.m2l.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotEmpty
	@NonNull
	@Min(1)
	@Max(100)
	Integer number;

	@NotEmpty
	@NonNull
	Date date;

	@ManyToOne
	@NonNull
	@JsonIgnoreProperties("users")
	@JoinColumn(nullable = false)
	User user;	

	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("commande")
	List<Article_commande> article_commandes = new ArrayList<>();
	
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("commande")
	List<Facture> factures = new ArrayList<>();
}