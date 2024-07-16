package com.library.app.Service.Auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${spring.security.token.secret}")
    private String secret;

    public String generate(String cpf) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("library-api").withSubject(cpf).withExpiresAt(exp()).sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("library-api").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant exp() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }
}




