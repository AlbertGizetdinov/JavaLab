package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignUpDto;
import ru.itis.exceptrions.ConfirmException;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.util.EmailUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    private final EmailUtil emailUtil;

    @Transactional
    @Override
    public void signUp(SignUpDto accountForm) {
        Account account = Account.builder()
                .firstName(accountForm.getFirstName())
                .lastName(accountForm.getLastName())
                .email(accountForm.getEmail())
                .state(Account.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .password(accountForm.getPassword())
                .build();

        accountsRepository.save(account);

        Map<String, String> data = new HashMap<>();
        data.put("first_name", account.getFirstName());
        data.put("confirm_code", account.getConfirmCode());

        new Thread(() -> emailUtil.sendMail(account.getEmail(), "confirm", "confirm_mail", data)).start();

    }

    @Override
    public void confirmAccount(String confirmCode) throws ConfirmException {
        Optional<Account> account = accountsRepository.findByConfirmCode(confirmCode);
        if (account.isPresent() && account.get().getState() == Account.State.NOT_CONFIRMED) {
            account.get().setState(Account.State.CONFIRMED);
            accountsRepository.save(account.get());
        } else {
            throw new ConfirmException();
        }
    }
}
