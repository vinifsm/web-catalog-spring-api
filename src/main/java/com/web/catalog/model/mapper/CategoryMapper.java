package com.web.catalog.model.mapper;

import com.web.catalog.model.entity.Category;
import com.web.catalog.model.entity.Store;

public class CategoryMapper {

    public static Category mapNew (String name, Store store) {
            Category entity = new Category();
            entity.setName(name);
            entity.setStore(store);
            return entity;
    }

}
