package com.entrenamiento.certero.api;

import com.entrenamiento.certero.service.UserService;
import com.entrenamiento.certero.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Mock
    private UserService userService; // Mock de UserService

    @InjectMocks
    private UserController userController; // UserController donde se inyectará el mock

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetUsers() {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        when(userService.getAllUsers()).thenReturn(List.of(user)); // Simula el comportamiento de getAllUsers()

        ResponseEntity<List<User>> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals("John Doe", response.getBody().get(0).getName());
    }
}
