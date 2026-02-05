package com.m2l.m2l.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
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
public class Article_commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotEmpty
	@NonNull
	@Positive
	Integer quantite;

	@NotEmpty
	@NonNull
	@Positive
	Float price_ht;

	@NotEmpty
	@NonNull
	@Positive
	Float price_ttc;

	@NotEmpty
	@NonNull
	@Positive
	Float total_price;

	@ManyToOne
	@JsonIgnoreProperties("commandes")
	@JoinColumn(nullable = false)
	Commande commande;


}