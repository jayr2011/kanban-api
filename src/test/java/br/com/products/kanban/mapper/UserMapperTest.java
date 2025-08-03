package br.com.products.kanban.mapper;

import br.com.products.kanban.dto.user.UserCreationRequestDTO;
import br.com.products.kanban.dto.user.UserViewDTO;
import br.com.products.kanban.model.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UserMapperTest {
    private UserEntity userEntity;
    private UserCreationRequestDTO userCreationRequestDTO;
    private UserViewDTO userViewDto;

    @BeforeEach
    void setUp() {
        userCreationRequestDTO = new UserCreationRequestDTO();
        userCreationRequestDTO.setName("test");
        userCreationRequestDTO.setEmail("jone@test.com");
        userCreationRequestDTO.setPassword("1234");
        userCreationRequestDTO.setDocumentNumber(123456L);
        userEntity = UserMapper.toEntity(userCreationRequestDTO);
    }

    @Test
    void shouldMapEmailFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDTO);
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
        UserEntity entity = UserMapper.toEntity(userCreationRequestDTO);
        assertEquals("test", entity.getName());
    }

    @Test
    void shouldMapPasswordFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDTO);
        assertEquals("1234", entity.getPassword());
    }

    @Test
    void shouldMapDocumentNumberFromDtoToEntity() {
        UserEntity entity = UserMapper.toEntity(userCreationRequestDTO);
        assertEquals(123456L, entity.getDocumentNumber());
    }

    @Test
    void toEntityShouldReturnNonNullUserEntity() {
        assertNotNull(userEntity);
    }

    @Test
    void toEntityShouldReturnUserEntityWithIncorrectName() {
        userCreationRequestDTO.setName(null);
        userEntity = UserMapper.toEntity(userCreationRequestDTO);
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
        String id = "123e4567-e89b-12d3-a456-426614174000";
        userEntity.setId(id);
        UserViewDTO dto = UserMapper.toViewDTO(userEntity);
        assertEquals(id, dto.getId());
    }

    @Test
    void toViewDtoShouldMapNameCorrectly() {
        userEntity.setName("Alice");
        UserViewDTO dto = UserMapper.toViewDTO(userEntity);
        assertEquals("Alice", dto.getName());
    }

    @Test
    void toViewDtoShouldMapEmailCorrectly() {
        userEntity.setEmail("alice@example.com");
        UserViewDTO dto = UserMapper.toViewDTO(userEntity);
        assertEquals("alice@example.com", dto.getEmail());
    }

    @Test
    void toViewDtoShouldMapDocumentNumberCorrectly() {
        userEntity.setDocumentNumber(987654321L);
        UserViewDTO dto = UserMapper.toViewDTO(userEntity);
        assertEquals(987654321L, dto.getDocumentNumber());
    }
    
    @Test
    void toViewDtoShouldReturnDistinctInstances() {
        UserViewDTO first = UserMapper.toViewDTO(userEntity);
        UserViewDTO second = UserMapper.toViewDTO(userEntity);
        assertNotSame(first, second);
    }
    
    @Test
    void toViewDtoShouldReturnNullIdWhenIdNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDTO dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getId());
    }

    @Test
    void toViewDtoShouldReturnNullNameWhenNameNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDTO dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getName());
    }

    @Test
    void toViewDtoShouldReturnNullEmailWhenEmailNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDTO dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getEmail());
    }

    @Test
    void toViewDtoShouldReturnNullDocumentNumberWhenDocumentNumberNotSet() {
        UserEntity empty = new UserEntity();
        UserViewDTO dto = UserMapper.toViewDTO(empty);
        assertNull(dto.getDocumentNumber());
    }
}