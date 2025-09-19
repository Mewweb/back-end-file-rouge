package com.m2l.m2l.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NotEmpty
	@NonNull
	@Size(min=3, max=50)
	String lastname;
	@NonNull
	@NotEmpty
	@Size(min=3, max=50)
	String firstname;
	@NonNull
	@NotEmpty
	@Email
	String email;
	@NotEmpty
	@NonNull
	@Size(min=100, max=255)
	String password;
	@NonNull
	@NotEmpty
	@Default
	Boolean is_admin = false;
	@NotEmpty
	@NonNull
	@Size(min=2, max=100)
	String adresse_facturation;
	@NotEmpty
	@NonNull
	@Size(min=2, max=100)
	String adresse_livraison;
	@JsonIgnoreProperties("articles")
	@NonNull
	@ManyToMany(cascade= {CascadeType.REFRESH, CascadeType.MERGE})
	List<Article> articles;
}
