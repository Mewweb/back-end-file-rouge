package com.m2l.m2l.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticle{
	Integer id;
	String title;
	Integer width;
	Integer height;
	Integer thickness;
	String number_isbn;
	Integer price;
	Integer stock;
	Integer editor;
	Integer book;
}
