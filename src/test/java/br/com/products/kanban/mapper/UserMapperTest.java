package br.com.products.kanban.mapper;

import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.model.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UserMapperTest {
    private UserEntity userEntity;
    private UserCreationRequestDto userCreationRequestDto;
    private UserViewDto userViewDto;

    @BeforeEach
    void setUp() {
        userCreationRequestDto = new UserCreationRequestDto();
        userCreationRequestDto.setName("test");
        userCreationRequestDto.setEmail("jone@test.com");
        userCreationRequestDto.setPassword("1234");
        userCreationRequestDto.setDocumentNumber(123456L);
        userEntity = UserMapper.toEntity(userCreationRequestDto);
    }

    @Test
    void shouldMapEmailFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDto);
        assertEquals("jone@test.com", entity.getEmail());
    }

    @Test
    void toEntityShouldThrowExceptionWhenDtoIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserMapper.toEntity(null);
        });
    }

    @Test
    void shouldMapNameFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDto);
        assertEquals("test", entity.getName());
    }

    @Test
    void shouldMapPasswordFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDto);
        assertEquals("1234", entity.getPassword());
    }

    @Test
    void shouldMapDocumentNumberFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDto);
        assertEquals(123456L, entity.getDocumentNumber());
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

    @Test
    void toViewDTOShouldThrowExceptionWhenEntityIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserMapper.toViewDTO(null);
        });
    }


    @Test
    void toViewDtoShouldReturnNonNull() {
        userViewDto = UserMapper.toViewDTO(userEntity);
        assertNotNull(userViewDto);
    }
    
    @Test
    void toViewDtoShouldMapIdCorrectly() {
        java.util.UUID id = java.util.UUID.randomUUID();
        userEntity.setId(id);
        UserViewDto dto = UserMapper.toViewDTO(userEntity);
        assertEquals(id, dto.getId());
    }

    @Test
    void toViewDtoShouldMapNameCorrectly() {
        userEntity.setName("Alice");
        UserViewDto dto = UserMapper.toViewDTO(userEntity);
        assertEquals("Alice", dto.getName());
    }

    @Test
    void toViewDtoShouldMapEmailCorrectly() {
        userEntity.setEmail("alice@example.com");
        UserViewDto dto = UserMapper.toViewDTO(userEntity);
        assertEquals("alice@example.com", dto.getEmail());
    }

    @Test
    void toViewDtoShouldMapDocumentNumberCorrectly() {
        userEntity.setDocumentNumber(987654321L);
        UserViewDto dto = UserMapper.toViewDTO(userEntity);
        assertEquals(987654321L, dto.getDocumentNumber());
    }
    
    @Test
    void toViewDtoShouldReturnDistinctInstances() {
        UserViewDto first = UserMapper.toViewDTO(userEntity);
        UserViewDto second = UserMapper.toViewDTO(userEntity);
        assertNotSame(first, second);
    }
    
    @Test
    void toViewDtoShouldReturnNullIdWhenIdNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDto dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getId());
    }

    @Test
    void toViewDtoShouldReturnNullNameWhenNameNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDto dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getName());
    }

    @Test
    void toViewDtoShouldReturnNullEmailWhenEmailNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDto dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getEmail());
    }

    @Test
    void toViewDtoShouldReturnNullDocumentNumberWhenDocumentNumberNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDto dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getDocumentNumber());
    }
}