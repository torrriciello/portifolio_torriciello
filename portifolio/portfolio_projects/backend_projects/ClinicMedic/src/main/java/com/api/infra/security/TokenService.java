package com.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * Service responsible for JWT lifecycle: generation, signing, and verification.
 */
@Service
public class TokenService {

    @Value("${api.security.token}")
    private String secret;

    private static final String ISSUER = "API ReciclaPro";

    /**
     * Generates a signed JWT for a specific user.
     * Includes the email as subject and the user ID as a custom claim.
     */
    public String gerarToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(dataExpiration())
                    .withClaim("id", user.getId()) // Custom claim for easier identification
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    /**
     * Validates the token and retrieves the subject (email).
     * Throws an exception if the token is tampered with or expired.
     */
    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT Token!");
        }
    }

    /**
     * Sets expiration to 2 hours from now, adjusted to the local timezone.
     */
    private Instant dataExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}