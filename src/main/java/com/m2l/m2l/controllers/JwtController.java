package com.m2l.m2l.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.dto.JwtResponseDto;
import com.m2l.m2l.dto.UserRequestDto;
import com.m2l.m2l.entities.User;
import com.m2l.m2l.services.TokenService;
import com.m2l.m2l.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class JwtController {

    private TokenService tokenService;
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponseDto authenticate(@RequestBody UserRequestDto userDto) {
        log.info("Demande de jeton avec {} ", userDto.getGrantType().name());
        if (userDto.getGrantType().name().equalsIgnoreCase("password")) {
            log.info("Demande de jeton de la part de {}", userDto.getEmail());
            User user = userService.checkUser(userDto.getEmail(), userDto.getPassword());
            log.info("Accès autorisé pour l'utilisateur {}", userDto.getEmail());
            var accessToken = tokenService.generateAccessTokenFromAuthentication(user.getEmail(),
                    user.getRole().name());
            log.info("Jeton d'accès {} pour {}", accessToken, userDto.getEmail());
            var refreshToken = tokenService.generateRefreshToken(user.getEmail());
            log.info("Jeton de rafraichissement {} pour {}", refreshToken, userDto.getEmail());
            return new JwtResponseDto(accessToken, refreshToken);
        } else if (userDto.getGrantType().name().equalsIgnoreCase("refresh_token")) {
            var tokens = tokenService.generateTokensFromRefreshToken(userDto.getRefreshToken());
            log.info("Jeton d'accès {}", tokens.getAccessToken());
            log.info("Jeton de rafraichissement {}", tokens.getRefreshToken());
            return tokens;
        }
        return null;
    }
    


}
