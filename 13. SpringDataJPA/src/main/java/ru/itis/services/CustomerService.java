package ru.itis.services;

import ru.itis.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAll();

    List<CustomerDto> getByName(String name);
}
