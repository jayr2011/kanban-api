package br.com.products.kanban.controller;

import br.com.products.kanban.dto.LoginDTO;
import br.com.products.kanban.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    /**
     * Service responsible for login and authentication operations.
     */
    private final LoginService loginService;

    /**
     * Constructs a LoginController with the required LoginService dependency.
     *
     * @param loginService Service for login operations.
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Authenticates a user and returns a JWT token if credentials are valid.
     *
     * @param loginDto Data transfer object containing login credentials.
     * @return ResponseEntity containing the JWT token if authentication is successful,
     *         or an error message with HTTP 401 status if credentials are invalid.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDto) {
        return loginService.login(loginDto)
                .<ResponseEntity<?>>map(token -> ResponseEntity.ok(Map.of("token", token)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid credentials")));
    }
}