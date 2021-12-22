package ru.itis.repositories;

import ru.itis.dto.AccountDto;
import ru.itis.models.Account;

import java.util.List;

public interface AccountsRepository extends CrudRepository<Account> {
    List<AccountDto> searchByEmail(String email);
}
