package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"shop", "manufacturer", "purchases"})
@EqualsAndHashCode(exclude = {"shop", "manufacturer", "purchases"})
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Float price;

    private Integer amount;

    @ManyToOne()
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne()
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"))
    private List<Purchase> purchases;
}
