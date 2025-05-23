package br.com.products.kanban.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUserList() {
        List<UserViewDto> mockUsers = List.of(new UserViewDto());
        when(userService.findAllUsers()).thenReturn(mockUsers);

        ResponseEntity<List<UserViewDto>> response = usersController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
    }

    @Test
    void shouldReturnNoContentWhenUserListIsEmpty() {
        when(userService.findAllUsers()).thenReturn(List.of());

        ResponseEntity<List<UserViewDto>> response = usersController.getUsers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturnUserById() {
        UUID userId = UUID.randomUUID();
        UserViewDto user = new UserViewDto();
        when(userService.findById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<UserViewDto> response = usersController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenUserByIdDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userService.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<UserViewDto> response = usersController.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldCreateUser() {
        UserCreationRequestDto requestDto = new UserCreationRequestDto();
        UserViewDto savedUser = new UserViewDto();
        when(userService.createUser(requestDto)).thenReturn(savedUser);

        ResponseEntity<UserViewDto> response = usersController.createUser(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }
}
