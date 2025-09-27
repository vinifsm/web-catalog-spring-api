package com.web.catalog.service;

import com.web.catalog.controller.request.CategoryRequest;
import com.web.catalog.model.entity.Category;
import com.web.catalog.model.entity.Store;
import com.web.catalog.model.mapper.CategoryMapper;
import com.web.catalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreService storeService;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findByStore(UUID storeId) {
        return categoryRepository.findByStore_Id(storeId);
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category create (CategoryRequest request) throws Exception {
        Store store = storeService.findById(request.storeId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Category category = CategoryMapper.mapNew(request.name(), store);
        return save(category);
    }

    public Category update (UUID id, String name) throws Exception {
        Category category = findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        category.setName(name);
        return save(category);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}

