package com.web.catalog.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String sku;
    private String description;
    private Double price;

    @ManyToOne
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
}

