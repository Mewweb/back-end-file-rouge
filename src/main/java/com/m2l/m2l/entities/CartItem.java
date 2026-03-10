package com.m2l.m2l.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NonNull
	@ManyToOne
	@JsonIgnoreProperties("cartItems")
	@JoinColumn(nullable= false)
	User user;

	@NonNull
	@ManyToOne
	@JsonIgnoreProperties("cartItems")
	@JoinColumn(nullable = false)
	Article article;

	@NonNull
	@Positive
	@Max(1000)
	Integer quantity;
}