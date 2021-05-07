package com.spring.boot.phoneshop.repositories;

import com.spring.boot.phoneshop.entities.Category;
import com.spring.boot.phoneshop.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByCategory(String category);

    List<Product> findAllByCategory(Category category, Pageable pageable);

    long countByCategory(Category category);
}
