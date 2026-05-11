package com.m2l.m2l.services;

import com.m2l.m2l.dto.JwtResponseDto;

public interface TokenService {
    String generateAccessTokenFromAuthentication(String email, String roles);

    String generateRefreshToken(String email);

    JwtResponseDto generateTokensFromRefreshToken(String refreshToken);
}