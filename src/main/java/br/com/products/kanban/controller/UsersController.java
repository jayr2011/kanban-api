package br.com.products.kanban.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.products.kanban.DTO.UserDTO;
import br.com.products.kanban.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gerenciar-usuarios")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/usuario")
    public ResponseEntity<List<UserDTO>> getUser() {
        List<UserDTO> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> PostUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }
}
