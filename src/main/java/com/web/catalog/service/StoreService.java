package com.web.catalog.service;

import com.web.catalog.controller.request.StoreRequest;
import com.web.catalog.model.entity.Store;
import com.web.catalog.model.mapper.StoreMapper;
import com.web.catalog.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Optional<Store> findById(UUID id) {
        return storeRepository.findById(id);
    }

    public Optional<Store> findByIdentifier(String identifier) {
        return storeRepository.findByIdentifier(identifier).stream().findFirst();
    }

    public Store create(StoreRequest request) {
        Store store = StoreMapper.mapNew(request);
        return save(store);
    }

    public Store update(StoreRequest request, UUID id) throws Exception {
        Store store = findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return save(StoreMapper.mapExisting(store, request));
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public void delete(UUID id) {
        storeRepository.deleteById(id);
    }
}

