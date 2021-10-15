package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    Optional<User> findByName(String name);

    List<User> findAll();
}
