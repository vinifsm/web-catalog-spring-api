package com.web.catalog.service;

import com.web.catalog.controller.request.UserRequest;
import com.web.catalog.model.entity.User;
import com.web.catalog.model.entity.Store;
import com.web.catalog.model.mapper.UserMapper;
import com.web.catalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final StoreService storeService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByStore(UUID storeId) {
        return userRepository.findByStore_Id(storeId);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(UserRequest request) throws Exception {
        Store store = storeService.findById(request.storeId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        User user = UserMapper.mapNew(request, store);
        return save(user);
    }

    public User update(UserRequest request, UUID id) throws Exception {
        User user = findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Store store = storeService.findById(request.storeId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return save(UserMapper.mapExisting(user, request, store));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}

