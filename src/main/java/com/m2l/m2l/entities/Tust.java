package com.m2l.m2l.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Data
public class Tust {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NotEmpty
	@NonNull
	String title;
}
