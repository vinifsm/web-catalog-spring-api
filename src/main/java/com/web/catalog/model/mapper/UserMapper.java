package com.web.catalog.model.mapper;

import com.web.catalog.controller.request.UserRequest;
import com.web.catalog.model.entity.User;
import com.web.catalog.model.entity.Store;

public class UserMapper {

    public static User mapNew(UserRequest request, Store store) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setRole(request.role());
        user.setStore(store);
        return user;
    }

    public static User mapExisting(User user, UserRequest request, Store store) {
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setRole(request.role());
        user.setStore(store);
        return user;
    }
}
