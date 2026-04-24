package com.m2l.m2l.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NonNull
	@NotEmpty
	@Size(min = 1, max = 50)
	String title;

	@NonNull
	@Min(1)
	@Max(1000)
	Integer width;

	@NonNull
	@Min(1)
	@Max(1000)
	Integer height;

	@NonNull
	@Min(1)
	@Max(1000)
	Integer thickness;

	@NonNull
	@NotEmpty
	@Size(min = 10, max = 14)
	String number_isbn;

	@NonNull
	@Min(1)
	@Max(1000)
	@Positive
	Integer price;

	@NonNull
	@Min(0)
	@Max(1000)
	@Positive
	Integer stock;

	@Default
	@NonNull
	Boolean active = false;

	@Default
	LocalDateTime addDate = LocalDateTime.now();

	@Default
	LocalDateTime editDate = LocalDateTime.now();

	@ManyToOne
	@NonNull
	@JsonIgnoreProperties("articles")
	@JoinColumn(nullable = false)
	Editor editor;

	@JsonIgnoreProperties("articles")
	@ManyToOne
	@JoinColumn(nullable = false)
	Book book;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("article")
	List<Article_User> article_Users = new ArrayList<>();

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("article")
	List<CartItem> cartItems = new ArrayList<>();
}