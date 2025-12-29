package com.m2l.m2l.request;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class BookRequest {
	String title;
	String synopsis;
	Integer stock;
	String style;
	LocalDate date;
	String image;
	List<Integer> authorsId;
}