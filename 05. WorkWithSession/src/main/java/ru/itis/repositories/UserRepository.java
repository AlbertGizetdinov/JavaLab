package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    List<User> findAll();

    void save(User user);

    Optional<User> findByEmail(String email);
}
