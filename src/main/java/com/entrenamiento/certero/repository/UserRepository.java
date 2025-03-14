package com.entrenamiento.certero.repository;

import com.entrenamiento.certero.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("John Doe", 30));
        users.add(new User("Jane Smith", 25));
        users.add(new User("Alice Johnson", 35));
    }

    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }

    public boolean deleteByName(String name) {
        return users.removeIf(user -> user.getName().equals(name));
    }

    public User findByName(String name) {
        return users.stream()
                    .filter(user -> user.getName().equals(name))
                    .findFirst()
                    .orElse(null);
    }
}
