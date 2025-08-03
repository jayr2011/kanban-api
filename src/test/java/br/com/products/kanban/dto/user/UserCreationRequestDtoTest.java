package br.com.products.kanban.dto.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UserCreationRequestDTOTest {
    private UserCreationRequestDTO userCreationRequestDTO;

    @BeforeEach
    void setUp() {
        userCreationRequestDTO = new UserCreationRequestDTO();
    }

    @Test
    void nameShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDTO.getName());
    }

    @Test
    void emailShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDTO.getEmail());
    }

    @Test
    void passwordShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDTO.getPassword());
    }

    @Test
    void documentNumberShouldReturnNullWhenNotSet() {
        assertNull(userCreationRequestDTO.getDocumentNumber());
    }

    @Test
    void nameShouldNotMatchIncorrectValue() {
        userCreationRequestDTO.setName("John Doe");
        assertNotEquals("Jane Doe", userCreationRequestDTO.getName());
    }

    @Test
    void emailShouldNotMatchIncorrectValue() {
        userCreationRequestDTO.setEmail("john.doe@example.com");
        assertNotEquals("jane.doe@example.com", userCreationRequestDTO.getEmail());
    }

    @Test
    void passwordShouldNotMatchIncorrectValue() {
        userCreationRequestDTO.setPassword("securePassword123");
        assertNotEquals("wrongPassword", userCreationRequestDTO.getPassword());
    }

    @Test
    void documentNumberShouldNotMatchIncorrectValue() {
        userCreationRequestDTO.setDocumentNumber(1239974734L);
        assertNotEquals(9876543210L, userCreationRequestDTO.getDocumentNumber());
    }
    @Test
    void nameShouldBeSetAndRetrievedCorrectly() {
        userCreationRequestDTO.setName("John Doe");
        assertEquals("John Doe", userCreationRequestDTO.getName());
    }

    @Test
    void emailShouldBeSetAndRetrievedCorrectly() {
        userCreationRequestDTO.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", userCreationRequestDTO.getEmail());
    }

    @Test
    void passwordShouldBeSetAndRetrievedCorrectly() {
        userCreationRequestDTO.setPassword("securePassword123");
        assertEquals("securePassword123", userCreationRequestDTO.getPassword());
    }

    @Test
    void documentNumberShouldBeSetAndRetrieveCorrectly() {
        userCreationRequestDTO.setDocumentNumber(1239974734L);
        assertEquals(1239974734L, userCreationRequestDTO.getDocumentNumber());
    }
}
