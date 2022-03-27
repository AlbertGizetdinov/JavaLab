package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
