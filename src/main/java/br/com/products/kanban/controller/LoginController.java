package br.com.products.kanban.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.products.kanban.dto.LoginDto;
import br.com.products.kanban.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    public LoginController( LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto) {
        return loginService.authenticate(loginDto)
            ? ResponseEntity.ok("Login successful")
            : ResponseEntity.status(401).body("Invalid credentials");
    }
}
