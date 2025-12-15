package com.m2l.m2l.entities;

import com.m2l.m2l.enums.Role;
import jakarta.persistence.Column;
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
	@NotEmpty
	@NonNull
	@Size(min=3,max=50)
	String firstname;
	@Size(min=6,max=20)
	String phone_number;
	@Email
	@NonNull
	@NotEmpty
	@Size(min=10,max=100)
	String email;
	@NotEmpty
	@Column(unique = true)
	@NonNull
	@Size(min=10, max=255)
	String password;
	@NotEmpty
	@NonNull
	@Size(min=2, max=100)
	String billing_address;
	@NotEmpty
	@NonNull
	@Size(min=2, max=100)
	String delivery_address;
	@NonNull
	Role role;
	/*@JsonIgnoreProperties("users")
	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	List<Article> articles;*/
	
}