package br.com.products.kanban.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class UserEntityTest {
    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
    }

    @Test
    void shouldCorrectlyStoreAndRetrieveId() {
        String id = "123e4567-e89b-12d3-a456-426614174000";
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    void shouldCorrectlyStoreAndRetrieveName() {
        user.setName("John Doe");
        assertEquals("John Doe", user.getName());
    }

    @Test
    void shouldCorrectlyStoreAndRetrieveDocumentNumber() {
        user.setDocumentNumber(123456789L);
        assertEquals(123456789L, user.getDocumentNumber());
    }

    @Test
    void shouldCorrectlyStoreAndRetrieveEmail() {
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void shouldCorrectlyStoreAndRetrievePassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void shouldRejectInvalidEmailFormat() {
        user.setEmail("invalid-email");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("email should be valid")));
    }

    @Test
    public void shouldAcceptValidEmailFormat() {
        user.setEmail("valid@test.com");
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);

        assertTrue(violations.isEmpty() || violations.stream().noneMatch(v -> v.getMessage().equals("email should be valid")));
    }
}
