package br.com.products.kanban.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CryptoPasswordServiceTest {
    private CryptoPasswordService cryptoPasswordService;
    private String encodedPassword;
    private String rawPassword;

    @BeforeEach
    void setUp() {
        cryptoPasswordService = new CryptoPasswordService();
        rawPassword = "mySecurePassword";
        encodedPassword = cryptoPasswordService.encodePassword(rawPassword);
    }

    @Test
    void encodePasswordShouldReturnEncodedString() {
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void matchesShouldReturnTrueForMatchingPasswords() {
        assertTrue(cryptoPasswordService.matches(rawPassword, encodedPassword));
    }

    @Test
    void matchesShouldReturnFalseForNonMatchingPasswords() {
        assertFalse(cryptoPasswordService.matches("wrongPassword", encodedPassword));
    }

    @Test
    void matchesShouldReturnFalseForNullEncodedPassword() {
        assertFalse(cryptoPasswordService.matches(rawPassword, null));
    }
}