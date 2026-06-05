package com.m2l.m2l.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import com.m2l.m2l.dto.JwtResponseDto;
import com.m2l.m2l.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JwtEncoder encoder;
    @Autowired
    private JwtDecoder decoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String generateAccessTokenFromAuthentication(String email, String roles) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .issuer("spring-ws-jwt")
                .expiresAt(Instant.now().plusSeconds(2 * 60))
                .claim("role", roles)
                .subject(email)
                .build();
        var jeton = encoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return jeton;
    }

    @Override
    public String generateRefreshToken(String email) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .issuer("spring-ws-jwt")
                .expiresAt(LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.ofHours(0)))
                .subject(email)
                .build();
        var jeton = encoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return jeton;
    }

    @Override
    public JwtResponseDto generateTokensFromRefreshToken(String refreshToken) {
        var decodeJwt = decoder.decode(refreshToken);
        var email = decodeJwt.getSubject();
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("Utilisateur inexistant");
        }
        var role = user.getRole().name();
        return new JwtResponseDto(
                generateAccessTokenFromAuthentication(email, role),
                generateRefreshToken(email));
    }
}