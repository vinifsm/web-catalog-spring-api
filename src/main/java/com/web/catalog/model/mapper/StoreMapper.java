package com.web.catalog.model.mapper;

import com.web.catalog.controller.request.StoreRequest;
import com.web.catalog.model.entity.Store;

public class StoreMapper {

    public static Store mapNew(StoreRequest request) {
        Store store = new Store();
        store.setName(request.name());
        store.setDescription(request.description());
        store.setPhone(request.phone());
        store.setIdentifier(request.identifier());
        return store;
    }

    public static Store mapExisting(Store store, StoreRequest request) {
        store.setName(request.name());
        store.setDescription(request.description());
        store.setPhone(request.phone());
        store.setIdentifier(request.identifier());
        return store;
    }

}
