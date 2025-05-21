package br.com.products.kanban.dto.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class UserViewDtoTest {
    private UserViewDto userViewDto;

    @BeforeEach
    void setUp() {
        userViewDto = new UserViewDto();
    }


    @Test
    void idShouldBeNullWhenNotSet() {
        assertNull(userViewDto.getId());
    }

    @Test
    void nameShouldBeNullWhenNotSet() {
        assertNull(userViewDto.getName());
    }

    @Test
    void emailShouldBeNullWhenNotSet() {
        assertNull(userViewDto.getEmail());
    }

    @Test
    void documentNumberShouldBeNullWhenNotSet() {
        assertNull(userViewDto.getDocumentNumber());
    }

    @Test
    void idShouldNotMatchIncorrectValue() {
        UUID id = UUID.randomUUID();
        UUID differentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
        userViewDto.setId(id);
        assertNotEquals(differentId, userViewDto.getId());
    }

    @Test
    void nameShouldNotMatchIncorrectValue() {
        userViewDto.setName("jone");
        assertNotEquals("jane", userViewDto.getName());
    }

    @Test
    void emailShouldNotMatchIncorrectValue() {
        userViewDto.setEmail("jone@test.com");
        assertNotEquals("jane@test.com", userViewDto.getEmail());
    }

    @Test
    void documentNumberShouldNotMatchIncorrectValue() {
        userViewDto.setDocumentNumber(12304849L);
        assertNotEquals(98765432L, userViewDto.getDocumentNumber());
    }

    @Test
    void emptyStringShouldBeAcceptedForName() {
        userViewDto.setName("");
        assertEquals("", userViewDto.getName());
    }

    @Test
    void emptyStringShouldBeAcceptedForEmail() {
        userViewDto.setEmail("");
        assertEquals("", userViewDto.getEmail());
    }
    @Test
    void idShouldBeSetAndRetrievedCorrectly() {
        UUID id = UUID.randomUUID();
        userViewDto.setId(id);
        assertEquals(id, userViewDto.getId());
    }

    @Test
    void nameShouldBeSetAndRetrievedCorrectly() {
        userViewDto.setName("jone");
        assertEquals("jone", userViewDto.getName());
    }

    @Test
    void emailShouldBeSetAndRetrievedCorrectly() {
        userViewDto.setEmail("jone@test.com");
        assertEquals("jone@test.com", userViewDto.getEmail());
    }

    @Test
    void documentNumberShouldBeSetAndRetrievedCorrectly() {
        userViewDto.setDocumentNumber(12304849L);
        assertEquals(12304849L, userViewDto.getDocumentNumber());
    }
}
