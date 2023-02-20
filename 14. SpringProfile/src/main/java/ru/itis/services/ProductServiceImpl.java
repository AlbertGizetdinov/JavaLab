package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProductDto;
import ru.itis.models.Product;
import ru.itis.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto addProduct(ProductDto product) {
        Product p = Product.builder()
                .price(product.getPrice())
                .name(product.getName())
                .build();

        return ProductDto.from(productRepository.save(p));
    }

    @Override
    public ProductDto getById(Long id) {
        return ProductDto.from(productRepository.getById(id));
    }
}
