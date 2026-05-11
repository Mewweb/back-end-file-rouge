package com.m2l.m2l.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto{
	Integer id;
	String lastname;
	String firstname;
	String phone_number;
	String email;
	String billing_address;
	String delivery_address;
}