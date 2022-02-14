package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;

    @Autowired
    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> signIn(SignInForm signInForm) {
        Optional<User> userOptional = userRepository.findByEmail(signInForm.getEmail());

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(signInForm.getPassword())) {
            return userOptional;
        }
        return Optional.empty();
    }
}
