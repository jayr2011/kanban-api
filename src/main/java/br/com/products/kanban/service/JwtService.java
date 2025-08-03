package br.com.products.kanban.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * Service for handling JWT operations such as token generation, validation, and extraction.
 */
@Service
public class JwtService {
    /**
     * Secret key used for signing JWT tokens.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Expiration time for JWT tokens in milliseconds.
     */
    @Value("${jwt.expiration}")
    private long expirationMillis;

    /**
     * Retrieves the signing key for JWT operations.
     *
     * @return Key used for signing JWT tokens.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a JWT token for the given user ID.
     *
     * @param userId UUID of the user.
     * @return Generated JWT token as a String.
     */
    public String generateToken(@NotNull String userId) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts the user ID from the given JWT token.
     *
     * @param token JWT token as a String.
     * @return UUID of the user extracted from the token.
     */
    public UUID extractUserId(@NotNull String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return UUID.fromString(claims.getSubject());
    }

    /**
     * Validates the given JWT token.
     *
     * @param token JWT token as a String.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateToken(@NotNull String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}