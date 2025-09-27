package com.web.catalog.controller;

import com.web.catalog.controller.request.CategoryRequest;
import com.web.catalog.model.entity.Category;
import com.web.catalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(@RequestParam(required = false) UUID storeId) {
        return storeId != null ? categoryService.findByStore(storeId) : categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable(required = true) UUID id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    public List<Category> getByStore(@PathVariable(required = true) UUID storeId) {
        return categoryService.findByStore(storeId);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody(required = true) CategoryRequest category) throws Exception {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable(required = true) UUID id, @RequestParam(name = "name", required = true) String name) throws Exception {
        return ResponseEntity.ok(categoryService.update(id, name));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) UUID id) {
        categoryService.delete(id);
    }
}

