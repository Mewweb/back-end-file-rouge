package com.m2l.m2l.dto;

import com.m2l.m2l.enums.Langage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthor{
	Integer id;
	String lastname;
	String firstname;
	Langage langue;
}
