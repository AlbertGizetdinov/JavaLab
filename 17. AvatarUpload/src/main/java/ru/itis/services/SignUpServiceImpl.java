package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    @Transactional
    @Override
    public void signUp(SignUpDto accountForm, String storageFileName) {
        Account account = Account.builder()
                .firstName(accountForm.getFirstName())
                .lastName(accountForm.getLastName())
                .email(accountForm.getEmail())
                .password(accountForm.getPassword())
                .storageFileName(storageFileName)
                .build();

        accountsRepository.save(account);
    }
}
