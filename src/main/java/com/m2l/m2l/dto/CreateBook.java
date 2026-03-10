package com.m2l.m2l.dto;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBook {
	Integer id;
	String title;
	String synopsis;
	String style;
	LocalDate date;
	String image;
	Boolean active;
	List<Integer> authors;
}