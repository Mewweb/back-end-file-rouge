package com.m2l.m2l.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class Article {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	Integer id;
	@NonNull
	@NotEmpty
	@Size(min=1, max=50)
	String title;
	@NotEmpty
	@NonNull
	@Size(min=3, max=20)
	String format;
	
}
