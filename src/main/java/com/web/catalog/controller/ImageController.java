package com.web.catalog.controller;

import com.web.catalog.model.entity.Image;
import com.web.catalog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final String IMAGE_DIR = "../images/";

    @GetMapping
    public List<Image> getAll() {
        return imageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getById(@PathVariable UUID id) {
        return imageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<byte[]> getImageFile(@PathVariable String filename) throws IOException {
        Path imagePath = Paths.get(IMAGE_DIR, filename);
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Files.readAllBytes(imagePath);
        String extension = StringUtils.getFilenameExtension(filename);
        MediaType mediaType = extension != null && extension.equalsIgnoreCase("jpg") ?
                MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + filename + "\"")
                .contentType(mediaType)
                .body(imageBytes);
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file,
                                             @RequestParam("type") String type) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        UUID uuid = UUID.randomUUID();
        Image image = new Image();
        image.setId(uuid);
        image.setFileName(type + "_" + uuid.toString());
        image.setUrl("/api/images/file/" + image.getFileName());
        Image savedImage = imageService.save(image);

        Path targetPath = Paths.get(IMAGE_DIR, image.getFileName());
        Files.copy(file.getInputStream(), targetPath);

        return ResponseEntity.ok(savedImage);
    }

    @PutMapping("/upload/{id}")
    public ResponseEntity<Image> update(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        Optional<Image> optionalImage = imageService.findById(id);
        if (optionalImage.isEmpty()) return ResponseEntity.notFound().build();

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Image image = optionalImage.get();
        Path targetPath = Paths.get(IMAGE_DIR, image.getFileName());
        Files.copy(file.getInputStream(), targetPath);

        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) throws IOException {
        imageService.findById(id).ifPresent(image -> {
            Path imagePath = Paths.get(IMAGE_DIR, image.getFileName());
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException ignored) {
            }

            imageService.delete(id);
        });
    }
}
