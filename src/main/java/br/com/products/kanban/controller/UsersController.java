package br.com.products.kanban.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.products.kanban.dto.user.UserViewDTO;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.repository.UserRepository;
import br.com.products.kanban.dto.user.UserCreationRequestDTO;
import br.com.products.kanban.service.JwtService;
import br.com.products.kanban.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing user-related endpoints.
 */
@RestController
@RequestMapping("/sec")
public class UsersController {
    /**
     * Service for user management operations.
     */
    private final UserService userService;

    /**
     * Service for JWT token operations.
     */
    private final JwtService jwtService;

    /**
     * Repository for accessing user data.
     */
    private final UserRepository userRepository;

    /**
     * Constructs a UsersController with required dependencies.
     *
     * @param userService Service for user management operations.
     * @param jwtService Service for JWT token operations.
     * @param userRepository Repository for accessing user data.
     */
    public UsersController(UserService userService, JwtService jwtService, UserRepository userRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users in the system.
     *
     * @return ResponseEntity with a list of UserViewDTO if users exist, or 204 No Content if empty.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserViewDTO>> getUsers() {
        List<UserViewDTO> users = userService.findAllUsers();
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The user's unique identifier (as String).
     * @return ResponseEntity with UserViewDTO if found, or 404 Not Found if not.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable String id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes all users from the system.
     *
     * @return ResponseEntity with HTTP 200 status if all users are deleted successfully.
     */
    @DeleteMapping("/users/deleteAll")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAll();
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves the currently authenticated user's information based on the JWT token.
     *
     * @param authorizationHeader The Authorization header containing the JWT token.
     * @return ResponseEntity with UserEntity if found and token is valid, 401 Unauthorized if token is invalid, or 404 Not Found if user does not exist.
     */
    @GetMapping("/user/me")
    public ResponseEntity<?> getMe(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String userId = jwtService.extractUserId(token);
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    /**
     * Creates a new user.
     *
     * @param userDto DTO containing user creation information.
     * @return ResponseEntity with the created UserViewDTO and HTTP 201 status.
     */
    @PostMapping("/addUser")
    public ResponseEntity<UserViewDTO> createUser(@Valid @RequestBody UserCreationRequestDTO userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }
}