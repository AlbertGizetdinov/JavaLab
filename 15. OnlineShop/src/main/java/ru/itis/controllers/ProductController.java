package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dto.ProductDto;
import ru.itis.services.ProductService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProductsPage(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(String name, Float price) {
        ProductDto product = ProductDto.builder()
                .name(name)
                .price(price)
                .build();
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/update")
    public String getUpdatePage() {
        return "update_product";
    }

    @PostMapping("/update")
    public String updateProduct(Long id, String name, Float price) {
        ProductDto product = ProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String getDeletePage() {
        return "delete_product";
    }

    @PostMapping("/delete")
    public String deleteProduct(Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}

