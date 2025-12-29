package com.m2l.m2l.request;

import lombok.Data;

@Data
public class ArticleRequest {
	String title;
	String format;
	Integer bookId;
}