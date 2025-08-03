package br.com.products.kanban.dto.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UserCreationRequestDtoTest {
    private UserCreationRequestDTO userCreationRequestDto;

    @BeforeEach
    void setUp() {
        userCreationRequestDto = new UserCreationRequestDTO();
    }

    @Test
    void nameShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDto.getName());
    }

    @Test
    void emailShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDto.getEmail());
    }

    @Test
    void passwordShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDto.getPassword());
    }

    @Test
    void documentNumberShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDto.getDocumentNumber());
    }

    @Test
    void nameShouldNotMatchIncorrectValue() {
        userCreationRequestDto.setName("John Doe");
        assertNotEquals("Jane Doe", userCreationRequestDto.getName());
    }

    @Test
    void emailShouldNotMatchIncorrectValue() {
        userCreationRequestDto.setEmail("john.doe@example.com");
        assertNotEquals("jane.doe@example.com", userCreationRequestDto.getEmail());
    }

    @Test
    void passwordShouldNotMatchIncorrectValue() {
        userCreationRequestDto.setPassword("securePassword123");
        assertNotEquals("wrongPassword", userCreationRequestDto.getPassword());
    }

    @Test
    void documentNumberShouldNotMatchIncorrectValue() {
        userCreationRequestDto.setDocumentNumber(1239974734L);
        assertNotEquals(9876543210L, userCreationRequestDto.getDocumentNumber());
    }
    @Test
    void nameShouldBeSetAndRetrievedCorrectly() {
        userCreationRequestDto.setName("John Doe");
        assertEquals("John Doe", userCreationRequestDto.getName());
    }

    @Test
    void emailShouldBeSetAndRetrievedCorrectly() {
        userCreationRequestDto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", userCreationRequestDto.getEmail());
    }

    @Test
    void passwordShouldBeSetAndRetrievedCorrectly() {
        userCreationRequestDto.setPassword("securePassword123");
        assertEquals("securePassword123", userCreationRequestDto.getPassword());
    }

    @Test
    void documentNumberShouldBeSetAndRetrieveCorrectly() {
        userCreationRequestDto.setDocumentNumber(1239974734L);
        assertEquals(1239974734L, userCreationRequestDto.getDocumentNumber());
    }
}
