package ru.itis.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"customer", "products"})
@EqualsAndHashCode(exclude = {"customer", "products"})
public class Purchase {
    private Long id;
    private String name;
    private Float price;
    private Customer customer;

    private List<Product> products;
}
