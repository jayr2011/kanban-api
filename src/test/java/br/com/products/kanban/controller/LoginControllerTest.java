package br.com.products.kanban.controller;

import br.com.products.kanban.dto.LoginDto;
import br.com.products.kanban.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        LoginDto loginDto = new LoginDto();
        when(loginService.authenticate(loginDto)).thenReturn(true);

        ResponseEntity<String> response = loginController.login(loginDto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    void testLoginFailure() {
        LoginDto loginDto = new LoginDto();
        when(loginService.authenticate(loginDto)).thenReturn(false);

        ResponseEntity<String> response = loginController.login(loginDto);

        assertEquals(401, response.getStatusCode().value());
        assertEquals("Invalid credentials", response.getBody());
    }
}
