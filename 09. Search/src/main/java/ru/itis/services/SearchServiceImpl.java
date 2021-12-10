package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.AccountDto;
import ru.itis.repositories.AccountsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final AccountsRepository accountsRepository;

    @Override
    public List<AccountDto> searchUsersByEmail(String email) {
        return accountsRepository.searchByEmail(email);
    }
}
