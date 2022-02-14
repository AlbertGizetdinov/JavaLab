package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.AccountDto;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;

import java.util.List;

import static ru.itis.dto.AccountDto.from;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public List<AccountDto> getAccounts() {
        return from(accountsRepository.findAll());
    }

    @Override
    public void addAccount(Account account) {
        accountsRepository.save(account);
    }
}
