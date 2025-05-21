package br.com.products.kanban.mapper;

import br.com.products.kanban.dto.LoginDto;
import br.com.products.kanban.model.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class LoginMapperTest {
    private LoginDto loginDto;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("1234");
        userEntity = new UserEntity();
        userEntity = LoginMapper.toUserEntityFromResponseDto(loginDto);
    }

    @Test
    void mapperShouldCreateNonNullUserEntity() {
        assertNotNull(userEntity);
    }
    
    @Test
    void mapperShouldCopyEmailCorrectly() {
        assertEquals("test@example.com", userEntity.getEmail());
    }
    
    @Test
    void mapperShouldCopyPasswordCorrectly() {
        assertEquals("1234", userEntity.getPassword());
    }
}
