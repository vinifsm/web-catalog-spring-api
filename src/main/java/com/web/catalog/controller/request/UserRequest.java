package com.web.catalog.controller.request;

import com.web.catalog.model.entity.User;
import java.util.UUID;

public record UserRequest(String username,
                         String password,
                         User.Role role,
                         UUID storeId) {
}