package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .build();

        userRepository.save(user);
    }
}
