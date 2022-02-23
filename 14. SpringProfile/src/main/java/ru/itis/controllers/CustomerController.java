package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.CustomerDto;
import ru.itis.services.CustomerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/customers")
    private List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping(value = "/customer/{customerName}")
    private List<CustomerDto> getByName(@PathVariable("customerName") String name) {
        return customerService.getByName(name);
    }
}
