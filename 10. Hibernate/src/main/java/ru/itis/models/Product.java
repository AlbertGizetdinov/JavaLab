package ru.itis.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"shop", "manufacturer", "purchases"})
@EqualsAndHashCode(exclude = {"shop", "manufacturer", "purchases"})
public class Product {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer amount;
    private Shop shop;
    private Manufacturer manufacturer;
    private List<Purchase> purchases;
}
