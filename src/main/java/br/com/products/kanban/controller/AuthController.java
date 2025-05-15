package br.com.products.kanban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.products.kanban.DTO.LoginDTO;
import br.com.products.kanban.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return authService.authenticate(loginDTO.getEmail(), loginDTO.getPassword())
            ? ResponseEntity.ok("Login successful")
            : ResponseEntity.status(401).body("Invalid credentials");
    }
}
