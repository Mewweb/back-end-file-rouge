package com.m2l.m2l.dto;

import com.m2l.m2l.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUserDto {
	Integer id;
	Article article;
	Integer quantity;
}