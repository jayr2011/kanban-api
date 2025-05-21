package br.com.products.kanban.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class LoginDtoTest {
    private LoginDto loginDto;

    @BeforeEach
    void setUp() {
        loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password123");
        
    }

    @Test
    void testSetAndGetEmail() {
        assertEquals("test@example.com", loginDto.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        assertEquals("password123", loginDto.getPassword());
    }

    @Test
    void testAllArgs() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("user@domain.com");
        loginDto.setPassword("pass123");
        assertAll(
            () -> assertEquals("user@domain.com", loginDto.getEmail()),
            () -> assertEquals("pass123", loginDto.getPassword())
        );
    }

    @Test
    void testDefaultValues() {
        LoginDto defaultLoginDto = new LoginDto();
        assertNull(defaultLoginDto.getEmail());
        assertNull(defaultLoginDto.getPassword());
    }
}
