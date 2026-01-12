package com.m2l.m2l.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@NonNull
	@NotEmpty
	String number_isbn;
	@NonNull
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
	@JoinColumn(name="editor_id", nullable = false)
	Editor editor;
	@ManyToOne
	@NonNull
	@JoinColumn(name="book_id", nullable=false)
	Book book;
	/*@ManyToMany(mappedBy = "articles", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("articles")
	List<User> users;*/

}