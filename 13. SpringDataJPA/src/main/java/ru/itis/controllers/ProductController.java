package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ProductDto;
import ru.itis.services.ProductService;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/add/product")
    private ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }

    @GetMapping(value = "/product/{productId}")
    private ProductDto getById(@PathVariable("productId") Long id) {
        return productService.getById(id);
    }
}
