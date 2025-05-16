package br.com.products.kanban.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.products.kanban.DTO.userDTO.UserRequisitionDTO;
import br.com.products.kanban.DTO.userDTO.UserResponseDTO;
import br.com.products.kanban.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kanban")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserRequisitionDTO>> getUsers() {
        List<UserRequisitionDTO> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserRequisitionDTO> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserRequisitionDTO> PostUser(@Valid @RequestBody UserResponseDTO userDTO) {
        UserRequisitionDTO savedUserDTO = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }
}
