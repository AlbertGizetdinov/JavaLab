package ru.itis.services;

import ru.itis.dto.AccountDto;
import ru.itis.models.Account;

import java.util.List;

public interface AccountsService {
    List<AccountDto> getAccounts();

    void addAccount(Account account);
}
