package com.web.catalog.model.mapper;

import com.web.catalog.controller.request.ProductRequest;
import com.web.catalog.model.entity.Category;
import com.web.catalog.model.entity.Product;

public class ProductMapper {

    public static Product mapNew(ProductRequest request, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setSku(request.sku());
        product.setDescription(request.description());
        return product;
    }

    public static Product mapExisting(Product product, ProductRequest request, Category category) {
        product.setCategory(category);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setSku(request.sku());
        product.setDescription(request.description());
        return product;
    }
}
