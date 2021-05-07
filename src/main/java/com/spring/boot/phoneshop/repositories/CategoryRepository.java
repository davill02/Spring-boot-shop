package com.spring.boot.phoneshop.repositories;

import com.spring.boot.phoneshop.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category getCategoryByName(String name);
    List<Category> findAll();
}
