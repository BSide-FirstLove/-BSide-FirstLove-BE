package com.bside.love.auth.service;

import com.bside.love.auth.dto.AuthResponse;
import com.bside.love.auth.security.jwt.JwtToken;
import com.bside.love.auth.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthResponse updateToken(JwtToken jwtToken) {
        Claims claims = jwtToken.getTokenClaims();
        if (claims == null) {
            return null;
        }

        String socialId = claims.getSubject();

        JwtToken newAppToken = jwtTokenProvider.createUserAppToken(socialId);

        return AuthResponse.builder()
                .appToken(newAppToken.getToken())
                .build();
    }
}