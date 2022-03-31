package ru.itis.services;

import ru.itis.dto.SignUpDto;
import ru.itis.exceptrions.ConfirmException;

public interface SignUpService {
    void signUp(SignUpDto accountForm);

    void confirmAccount(String confirmCode) throws ConfirmException;
}
