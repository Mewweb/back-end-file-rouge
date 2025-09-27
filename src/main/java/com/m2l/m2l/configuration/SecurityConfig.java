package com.m2l.m2l.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration // Indique que c'est une classe de configuration
@EnableWebSecurity // Active la sécurité Web de spring security
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // désactive le CSRF, je le désactive car pour l'instant j'utilise le basic Auth
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // active CORS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/m2l/**").authenticated() // Indique que tout ce qui commence par m2l à besoin d'une authentifcation basique
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000")); // Seul le site Nuxt à l'autorisation de l'utilisé
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // J'autorise ces requêtes
        config.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Il peut les en-tête Authorization et Content-Type
        config.setAllowCredentials(true); // Permet d'envoyer des cookies ou des credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Applique cette config à toutes les routes du backend
        return source;
    }
}
