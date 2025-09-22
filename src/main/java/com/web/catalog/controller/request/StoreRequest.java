package com.web.catalog.controller.request;

public record StoreRequest(String name,
                           String description,
                           String phone,
                           String identifier) {
}
