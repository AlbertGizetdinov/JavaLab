package ru.itis.services;

import ru.itis.dto.AccountDto;

import java.util.List;

public interface SearchService {
    List<AccountDto> searchUsersByEmail(String email);
}
