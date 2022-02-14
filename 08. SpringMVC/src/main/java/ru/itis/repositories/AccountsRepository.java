package ru.itis.repositories;

import ru.itis.models.Account;

import java.util.List;

public interface AccountsRepository {
    List<Account> findAll();

    void save(Account account);
}
