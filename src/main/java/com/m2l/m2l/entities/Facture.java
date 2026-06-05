package com.m2l.m2l.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotEmpty
	@NonNull
	@Size(min = 1, max = 255)
	String numberString;

	@NotEmpty
	@NonNull

	@Size(min = 1, max = 255)
	String pdf;

	@ManyToOne
	@NonNull
	@JsonIgnoreProperties("commandes")
	@JoinColumn(nullable = false)
	Commande commande;
}