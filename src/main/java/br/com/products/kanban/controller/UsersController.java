package br.com.products.kanban.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kanban")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserViewDto>> getUsers() {
        List<UserViewDto> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserViewDto> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserViewDto> PostUser(@Valid @RequestBody UserCreationRequestDto userDTO) {
        UserViewDto savedUserDTO = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }
}
