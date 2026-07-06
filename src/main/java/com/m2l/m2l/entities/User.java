package com.m2l.m2l.entities;

import java.util.List;
import com.m2l.m2l.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
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
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotEmpty
	@NonNull
	@Size(min = 3, max = 50)
	String lastname;

	@NotEmpty
	@NonNull
	@Size(min = 3, max = 50)
	String firstname;

	@Size(min = 6, max = 20)
	String phone_number;

	@Email
	@NonNull
	@NotEmpty
	@Size(min = 5, max = 254)
	@Column(unique = true)
	String email;

	@NotEmpty
	@NonNull
	@Size(min = 8, max = 255)
	String password;

	@NotEmpty
	@NonNull
	@Size(min = 2, max = 100)
	String billing_address;

	@NotEmpty
	@NonNull
	@Size(min = 2, max = 100)
	String delivery_address;

	@NonNull
	Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Article_User> article_Users;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<CartItem> cartItems;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Commande> commandes;
}