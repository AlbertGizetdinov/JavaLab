package ru.itis.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
public class Manufacturer {
    private Long id;
    private String name;
    private String description;

    private List<Product> products;
}
