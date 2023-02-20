package ru.itis.services;

import ru.itis.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void addProduct(ProductDto productDto);

    List<ProductDto> getAll();

    void updateProduct(ProductDto productDto);

    void deleteProduct(Long id);
}
