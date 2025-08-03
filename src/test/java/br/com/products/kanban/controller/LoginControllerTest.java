package br.com.products.kanban.controller;

import br.com.products.kanban.dto.LoginDTO;
import br.com.products.kanban.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;

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
        LoginDTO loginDto = new LoginDTO();
        when(loginService.login(loginDto)).thenReturn(java.util.Optional.of("fake-jwt-token"));

        ResponseEntity<?> response = loginController.login(loginDto);

        assertEquals(200, response.getStatusCode().value());
        assertInstanceOf(Map.class, response.getBody());
        java.util.Map<?,?> body = (java.util.Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("fake-jwt-token", body.get("token"));
    }

    @Test
    void testLoginFailure() {
        LoginDTO loginDto = new LoginDTO();
        when(loginService.login(loginDto)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = loginController.login(loginDto);

        assertEquals(401, response.getStatusCode().value());
        assertInstanceOf(Map.class, response.getBody());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("Invalid credentials", body.get("error"));
    }
}
