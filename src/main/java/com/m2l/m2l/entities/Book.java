package com.m2l.m2l.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
	@Size(min=2, max=30)
	String editor;
	@NonNull
	@Min(0)
	@Max(1000)
	Integer stock;
	@NonNull
	@NotEmpty
	@Size(min=3, max=255)
	String style;
	@NonNull
	@NotEmpty
	@Size(min=3, max=255)
	String image;
	@NotEmpty
	@NonNull
	@Size(min=3, max=10)
	String format;
	@NonNull
	@NotEmpty
	String number_isbn;
	
	@JsonIgnoreProperties("books")
	@NonNull
	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	List<Author> authors;
	
	@ManyToOne
	@NonNull
	@JoinColumn(name="article_id", nullable = false)
	Article article;
	/*@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
		    name = "book_author",  // <-- change ici
		    joinColumns = @JoinColumn(name = "book_id"),
		    inverseJoinColumns = @JoinColumn(name = "author_id")
		)
    @JsonIgnoreProperties("books")
	@NotNull
	List<Author> authors;*/
	
}
