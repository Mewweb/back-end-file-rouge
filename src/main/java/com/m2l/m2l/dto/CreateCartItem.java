package com.m2l.m2l.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartItem {
	Integer user;
	Integer quantity;
	Integer article;
}
