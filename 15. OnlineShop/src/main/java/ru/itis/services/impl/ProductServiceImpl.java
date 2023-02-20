package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProductDto;
import ru.itis.models.Product;
import ru.itis.repositories.ProductRepository;
import ru.itis.services.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .price(productDto.getPrice())
                .name(productDto.getName())
                .build();

        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAll() {
        return ProductDto.from(productRepository.findAll());
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        if (productRepository.findById(productDto.getId()).isPresent()) {
            Product product = Product.builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .build();
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
