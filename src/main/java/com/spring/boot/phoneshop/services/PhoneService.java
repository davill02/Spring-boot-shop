package com.spring.boot.phoneshop.services;

import com.spring.boot.phoneshop.entities.Category;
import com.spring.boot.phoneshop.entities.Product;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    Optional<Product> getById(Long key);

    Long getCount(String category, String field, String order, String page);

    List<Product> getByParameters(String category, String field, String order, String page);

    List<Category> getAllCategories();

}
