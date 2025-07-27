package br.com.products.kanban.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.repository.UserRepository;
import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.service.JwtService;
import br.com.products.kanban.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
     * @param userService Service for user management.
     * @param jwtService Service for JWT operations.
     * @param userRepository Repository for user data.
     */
    public UsersController(UserService userService, JwtService jwtService, UserRepository userRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing a list of UserViewDto if users exist,
     *         or no content status if the list is empty.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserViewDto>> getUsers() {
        List<UserViewDto> users = userService.findAllUsers();
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id UUID of the user.
     * @return ResponseEntity containing UserViewDto if found, or not found status otherwise.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserViewDto> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the currently authenticated user's information based on the JWT token.
     *
     * @param authorizationHeader Authorization header containing the JWT token.
     * @return ResponseEntity containing the UserEntity if found and token is valid,
     *         unauthorized status if token is invalid, or not found status if user does not exist.
     */
    @GetMapping("/user/me")
    public ResponseEntity<?> getMe(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        UUID userId = jwtService.extractUserId(token);
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    /**
     * Creates a new user.
     *
     * @param userDto Data transfer object containing user creation information.
     * @return ResponseEntity containing the created UserViewDto with HTTP 201 status.
     */
    @PostMapping("/addUser")
    public ResponseEntity<UserViewDto> createUser(@Valid @RequestBody UserCreationRequestDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }
}