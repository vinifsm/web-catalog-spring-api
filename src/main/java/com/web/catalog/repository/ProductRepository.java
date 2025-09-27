package com.web.catalog.repository;

import com.web.catalog.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByCategory_Id(UUID categoryId, Pageable pageable);
    Page<Product> findByStore_Id(UUID storeId, Pageable pageable);
    Page<Product> findByCategory_IdAndStore_Id(UUID categoryId, UUID storeId, Pageable pageable);
}


