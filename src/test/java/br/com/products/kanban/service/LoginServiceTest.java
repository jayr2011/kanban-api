package br.com.products.kanban.service;

import br.com.products.kanban.dto.LoginDTO;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CryptoPasswordService cryptoPassword;

    @Mock
    private JwtService jwtService;

    private LoginService loginService;

    private LoginDTO loginDto;

    private UserEntity userEntity;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        loginService = new LoginService(userRepository, cryptoPassword, jwtService);
        loginDto = new LoginDTO();
        userEntity = new UserEntity();
    }

    @Test
    void authenticateShouldReturnFalseWhenPasswordIsIncorrect() {
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("wrongPassword");

        userEntity.setEmail("test@example.com");
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));
        when(cryptoPassword.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        boolean result = loginService.authenticate(loginDto);

        assertFalse(result);
    }

    @Test
    void authenticateShouldReturnFalseWhenEmailNotFound() {
        loginDto.setEmail("notfound@example.com");
        loginDto.setPassword("password123");

        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        boolean result = loginService.authenticate(loginDto);

        assertFalse(result);
    }

    @Test
    void authenticateShouldReturnTrueWhenPasswordIsCorrect() {
        // Arrange: set loginDto and userEntity with matching credentials
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password123");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password123");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));
        when(cryptoPassword.matches("password123", "password123")).thenReturn(true);

        // Act
        boolean result = loginService.authenticate(loginDto);

        // Assert
        assertTrue(result);
    }

}
