package com.m2l.m2l.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.m2l.m2l.enums.Langage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NonNull
	@NotEmpty
	@Size(min = 3, max = 50)
	String lastname;

	@NonNull
	@NotEmpty
	@Size(min = 3, max = 50)
	String firstname;

	@NonNull
	Langage langue;

	// @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
	@ManyToMany
	@JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	@JsonIgnoreProperties("authors") // évite la récursion infinie
	List<Book> books;
}