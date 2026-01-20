package com.m2l.m2l.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotEmpty
	@NonNull
	@Size(min=2, max=200)
	String title;

	@NonNull
	@NotEmpty
	@Lob
	@Size(min=20)
	@Column(columnDefinition= "TEXT")
	String synopsis;

	@NonNull
	@NotEmpty
	@Size(min=3, max=255)
	String style;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	LocalDate date;

	@NonNull
	@NotEmpty
	@Size(min=3, max=255)
	String image;

	@Default
	Boolean active = false;

	@Default
	LocalDateTime addDate = LocalDateTime.now();

	@Default
	LocalDateTime editDate = LocalDateTime.now();

	@JsonIgnoreProperties("books")
	@NonNull
	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	List<Author> authors;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Article> articles = new ArrayList<>();
}