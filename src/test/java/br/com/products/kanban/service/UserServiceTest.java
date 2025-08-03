package br.com.products.kanban.service;

import br.com.products.kanban.dto.user.UserCreationRequestDTO;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CryptoPasswordService cryptoPasswordService;

    @Mock
    private Logger logger;

    @Mock
    private UserService userService;

    @Test
    void createUserShouldThrowExceptionWhenEmailAlreadyExists() {
        UserService userService = new UserService(userRepository, cryptoPasswordService);


        UserCreationRequestDTO dto = new UserCreationRequestDTO();
        dto.setEmail("existing@example.com");
        dto.setPassword("password123");

        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(new UserEntity()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.createUser(dto));

        assertEquals("Email already exists", exception.getMessage());
        verify(userRepository).findByEmail("existing@example.com");
        verifyNoMoreInteractions(userRepository);
    };

    @Test
    void createUserShouldReturnUserViewDtoWhenEmailDoesNotExist() {
        UserService userService = new UserService(userRepository, cryptoPasswordService);

        UserCreationRequestDTO userCreationRequestDto = new UserCreationRequestDTO();
        userCreationRequestDto.setEmail("example@examole.com");
        userCreationRequestDto.setPassword("password123");
        userCreationRequestDto.setName("exemplo");
        userCreationRequestDto.setDocumentNumber(123456789L);

        when(userRepository.findByEmail("example@examole.com")).thenReturn(Optional.empty());
        when(cryptoPasswordService.encodePassword("password123")).thenReturn("encodedPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("example@examole.com");
        userEntity.setPassword("encodedPassword");
        userEntity.setName("exemplo");
        userEntity.setDocumentNumber(123456789L);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        
        var resultDto = userService.createUser(userCreationRequestDto);

        assertEquals(userEntity.getEmail(), resultDto.getEmail());
        verify(userRepository).findByEmail("example@examole.com");
        verify(cryptoPasswordService).encodePassword("password123");
        verify(userRepository).save(any(UserEntity.class));
    };
};
