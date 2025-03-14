package com.entrenamiento.certero.service;

import com.entrenamiento.certero.repository.UserRepository;
import com.entrenamiento.certero.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository; // Mock de UserRepository

    @InjectMocks
    private UserService userService; // UserService donde se inyectará el mock

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllUsers() {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        when(userRepository.findAll()).thenReturn(List.of(user)); // Simula el comportamiento de findAll()

        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals("John Doe", users.get(0).getName());
    }
}
