package com.m2l.m2l.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	@NonNull
	@NotEmpty
	@Lob
	@Size(min=20, max=3000)
	@Column(columnDefinition = "TEXT", nullable = true)
	String summary;
	@NonNull
	@NotEmpty
	@Size(min=2, max=255)
	String reference;
	@ManyToMany(mappedBy = "articles",fetch = FetchType.EAGER)
	@JsonIgnoreProperties("articles")
	List<User> users;
	
}
