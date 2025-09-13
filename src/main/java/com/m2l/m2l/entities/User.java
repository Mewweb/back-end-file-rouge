package com.m2l.m2l.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Builder.Default;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@NonNull
	@Size(min=3, max=50)
	private String lastname;
	@NonNull
	@NotEmpty
	@Size(min=3, max=50)
	private String firstname;
	@NonNull
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@NonNull
	@Size(min=100, max=255)
	private String password;
	@NonNull
	@NotEmpty
	@Default
	private Boolean is_admin = false;
	@NotEmpty
	@NonNull
	@Size(min=2, max=100)
	private String adresse_facturation;
	@NotEmpty
	@NonNull
	@Size(min=2, max=100)
	private String adresse_livraison;
}
