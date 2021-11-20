package ru.itis.services;

import ru.itis.dto.SignInForm;
import ru.itis.models.User;

import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(SignInForm signInForm);
}
