package com.web.catalog.model.mapper;

import com.web.catalog.controller.request.ProductRequest;
import com.web.catalog.model.entity.Category;
import com.web.catalog.model.entity.Image;
import com.web.catalog.model.entity.Product;
import com.web.catalog.model.entity.Store;

public class ProductMapper {

    public static Product mapNew(ProductRequest request, Category category, Store store, Image image) {
        Product product = new Product();
        product.setCategory(category);
        product.setStore(store);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setSku(request.sku());
        product.setDescription(request.description());
        product.setImage(image);
        return product;
    }

    public static Product mapExisting(Product product, ProductRequest request, Category category, Store store, Image image) {
        product.setCategory(category);
        product.setStore(store);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setSku(request.sku());
        product.setDescription(request.description());
        product.setImage(image);
        return product;
    }
}
