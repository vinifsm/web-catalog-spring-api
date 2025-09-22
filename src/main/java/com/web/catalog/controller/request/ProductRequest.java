package com.web.catalog.controller.request;

import java.util.UUID;

public record ProductRequest(String name,
                             String sku,
                             String description,
                             Double price,
                             UUID categoryId) {
}
