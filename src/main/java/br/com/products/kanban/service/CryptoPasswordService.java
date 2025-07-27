package br.com.products.kanban.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for password encryption and verification using BCrypt.
 */
@Service
public class CryptoPasswordService {
    /**
     * BCryptPasswordEncoder instance used for encoding and matching passwords.
     */
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encodes a raw password using BCrypt.
     *
     * @param password Raw password to encode.
     * @return Encoded password as a String.
     */
    public String encodePassword(@NotNull String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Verifies if a raw password matches an encoded password.
     *
     * @param rawPassword Raw password to verify.
     * @param encodedPassword Encoded password to compare against.
     * @return true if the passwords match, false otherwise.
     */
    public boolean matches(@NotNull String rawPassword,@NotNull String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}