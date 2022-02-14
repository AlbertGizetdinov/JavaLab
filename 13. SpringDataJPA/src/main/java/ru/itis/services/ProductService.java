package ru.itis.services;

import ru.itis.dto.ProductDto;

public interface ProductService {
    ProductDto addProduct(ProductDto product);

    ProductDto getById(Long id);
}
