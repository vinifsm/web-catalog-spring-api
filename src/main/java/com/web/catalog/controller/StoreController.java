package com.web.catalog.controller;

import com.web.catalog.controller.request.StoreRequest;
import com.web.catalog.model.entity.Store;
import com.web.catalog.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public List<Store> getAll() {
        return storeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getById(@PathVariable UUID id) {
        return storeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/identity/{identifier}")
    public ResponseEntity<Store> getByIdentifier(@PathVariable String identifier) {
        return storeService.findByIdentifier(identifier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Store> create(@RequestBody StoreRequest store) {
        return ResponseEntity.ok(storeService.create(store));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> update(@PathVariable UUID id, @RequestBody StoreRequest store) throws Exception {
        return ResponseEntity.ok(storeService.update(store, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        storeService.delete(id);
    }
}

