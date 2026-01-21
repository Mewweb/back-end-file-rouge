package com.m2l.m2l.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class Editor{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NonNull
	@NotEmpty
	@Size(min=2, max=50)
	String title;

	@NonNull
	@NotEmpty
	@Lob
	@Size(min=20)
	@Column(columnDefinition = "TEXT")
	String description;

	@NonNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	LocalDate date;
	
	@OneToMany(mappedBy = "editor", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Article> articles = new ArrayList<>();
}