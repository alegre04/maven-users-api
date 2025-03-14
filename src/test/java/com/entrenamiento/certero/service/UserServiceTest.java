package com.entrenamiento.certero.service;

import com.entrenamiento.certero.domain.User;
import com.entrenamiento.certero.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Carlos Pérez", 28);
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Carlos Pérez", users.get(0).getName());
    }

    @Test
    void testAddUser() {
        doNothing().when(userRepository).save(user);

        userService.addUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRemoveUser() {
        when(userRepository.deleteByName(user.getName())).thenReturn(true);

        boolean result = userService.removeUser(user.getName());

        assertTrue(result);
        verify(userRepository, times(1)).deleteByName(user.getName());
    }

    @Test
    void testGetUserByName() {
        when(userRepository.findByName(user.getName())).thenReturn(user);

        User foundUser = userService.getUserByName(user.getName());

        assertNotNull(foundUser);
        assertEquals("Carlos Pérez", foundUser.getName());
    }
}
