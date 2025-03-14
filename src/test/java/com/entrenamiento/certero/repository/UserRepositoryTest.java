package com.entrenamiento.certero.repository;

import com.entrenamiento.certero.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertEquals(3, users.size());
    }

    @Test
    void testSave() {
        User user = new User("Carlos Pérez", 28);
        userRepository.save(user);

        User foundUser = userRepository.findByName("Carlos Pérez");
        assertNotNull(foundUser);
        assertEquals("Carlos Pérez", foundUser.getName());
    }

    @Test
    void testDeleteByName() {
        boolean result = userRepository.deleteByName("John Doe");
        assertTrue(result);

        User deletedUser = userRepository.findByName("John Doe");
        assertNull(deletedUser);
    }

    @Test
    void testFindByName() {
        User user = userRepository.findByName("Jane Smith");
        assertNotNull(user);
        assertEquals("Jane Smith", user.getName());
    }
}
