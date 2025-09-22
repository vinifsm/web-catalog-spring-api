package com.web.catalog.controller.request;

import java.util.UUID;

public record CategoryRequest(String name,
                              UUID storeId) {}
