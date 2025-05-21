package br.com.products.kanban.mapper;

import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.model.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UserMapperTest {
    private UserEntity userEntity;
    private UserCreationRequestDto userCreationRequestDto;

    @BeforeEach
    void setUp() {
        userCreationRequestDto = new UserCreationRequestDto();
        userCreationRequestDto.setName("test");
        userCreationRequestDto.setEmail("jone@teste.com");
        userCreationRequestDto.setPassword("1234");
        userCreationRequestDto.setDocumentNumber(123456L);
        userEntity = new UserEntity();
        userEntity = UserMapper.toEntity(userCreationRequestDto);
    }

    @Test
     void emailDtoToEmailEntity() {
          assertEquals("jone@teste.com", userEntity.getEmail());
    }

    @Test
    void nameDtoToNameEntity() {
        assertEquals("test", userEntity.getName());
    }

    @Test
    void passwordDtoToPasswordEntity() {
        assertEquals("1234", userEntity.getPassword());
    }

    @Test
    void documentNumberDtoToDocumentNumberEntity() {
        assertEquals(123456L, userEntity.getDocumentNumber());
    }

    @Test
    void toEntityShouldReturnNonNullUserEntity() {
        assertNotNull(userEntity);
    }

    @Test
    void toEntityShouldReturnUserEntityWithIncorrectName() {
        userCreationRequestDto.setName(null);
        userEntity = UserMapper.toEntity(userCreationRequestDto);
        assertNotEquals("test" ,userEntity.getName());
    }
}
