package com.web.catalog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String sku;
    private String description;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"products", "store"})
    private Category category;

    @Transient
    public UUID getCategoryId() {
        return category != null ? category.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties({"users", "categories", "image"})
    private Store store;

    @Transient
    public UUID getStoreId() {
        return store != null ? store.getId() : null;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @Transient
    public UUID getImageId() {
        return image != null ? image.getId() : null;
    }
}

