package com.web.catalog.service;

import com.web.catalog.model.entity.Image;
import com.web.catalog.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Optional<Image> findById(UUID id) {
        return imageRepository.findById(id);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public void delete(UUID id) {
        imageRepository.deleteById(id);
    }
}

