package com.web.catalog.service;

import com.web.catalog.controller.request.ProductRequest;
import com.web.catalog.model.entity.Category;
import com.web.catalog.model.entity.Image;
import com.web.catalog.model.entity.Product;
import com.web.catalog.model.entity.Store;
import com.web.catalog.model.mapper.ProductMapper;
import com.web.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final StoreService storeService;
    private final ImageService imageService;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByCategory(UUID categoryId, Pageable pageable) {
        return productRepository.findByCategory_Id(categoryId, pageable);
    }

    public Page<Product> findByStore(UUID storeId, Pageable pageable) {
        return productRepository.findByStore_Id(storeId, pageable);
    }

    public Page<Product> findByCategoryAndStore(UUID categoryId, UUID storeId, Pageable pageable) {
        return productRepository.findByCategory_IdAndStore_Id(categoryId, storeId, pageable);
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public Product create(ProductRequest request) throws Exception {
        Category category = categoryService.findById(request.categoryId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Store store = storeService.findById(request.storeId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        
        Image image = null;
        if (request.imageId() != null) {
            image = imageService.findById(request.imageId()).orElse(null);
        }
        
        Product product = ProductMapper.mapNew(request, category, store, image);
        return save(product);
    }

    public Product update(ProductRequest request, UUID id) throws Exception {
        Product product = findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Category category = categoryService.findById(request.categoryId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Store store = storeService.findById(request.storeId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        
        Image image = null;
        if (request.imageId() != null) {
            image = imageService.findById(request.imageId()).orElse(null);
        }
        
        return save(ProductMapper.mapExisting(product, request, category, store, image));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}

