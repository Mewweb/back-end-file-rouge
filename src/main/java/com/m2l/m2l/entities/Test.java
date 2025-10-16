package com.m2l.m2l.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Data
@Entity
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NotEmpty
	@NonNull
	String title;
	@ManyToOne
	@NonNull
	@JoinColumn(name="tust_id", nullable = false)
	Tust tust;
}
