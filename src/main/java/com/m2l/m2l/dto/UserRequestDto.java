package com.m2l.m2l.dto;

import com.m2l.m2l.enums.GrantType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	String email;
	String password;
	String refreshToken;
	GrantType grantType;
}
