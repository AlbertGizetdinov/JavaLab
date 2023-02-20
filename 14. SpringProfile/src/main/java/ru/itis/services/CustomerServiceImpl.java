package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.CustomerDto;
import ru.itis.repositories.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAll() {
        return CustomerDto.from(customerRepository.findAll());
    }

    @Override
    public List<CustomerDto> getByName(String name) {
        return CustomerDto.from(customerRepository.findAllByFirstNameLike("%" + name + "%"));
    }
}
