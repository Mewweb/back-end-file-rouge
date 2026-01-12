package com.m2l.m2l.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBook {
	String title;
	String synopsis;
	String style;
	LocalDate date;
	String image;
	List<Integer> authors;
}
