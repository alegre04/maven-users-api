package com.entrenamiento.certero.service;

import com.entrenamiento.certero.domain.User;
import com.entrenamiento.certero.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean removeUser(String name) {
        return userRepository.deleteByName(name);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
