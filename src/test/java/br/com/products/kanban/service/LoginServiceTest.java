package br.com.products.kanban.service;

import br.com.products.kanban.dto.LoginDto;
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

    private LoginService loginService;

    private LoginDto loginDto;

    private UserEntity userEntity;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        loginService = new LoginService();
        loginService.setUserRepository(userRepository);
        loginService.setCryptoPassword(cryptoPassword);
        loginDto = new LoginDto();
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
        loginDto.setEmail("notfound@example.com");
        loginDto.setPassword("password123");

        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.of(userEntity));
        when(cryptoPassword.matches("password123", userEntity.getPassword())).thenReturn(true);

        boolean result = loginService.authenticate(loginDto);

        assertTrue(result);
    };

}
