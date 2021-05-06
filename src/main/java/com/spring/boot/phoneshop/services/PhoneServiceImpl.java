package com.spring.boot.phoneshop.services;

import com.spring.boot.phoneshop.annotaions.AddIdToModelName;
import com.spring.boot.phoneshop.entities.Category;
import com.spring.boot.phoneshop.entities.Product;
import com.spring.boot.phoneshop.annotaions.IncreasePrice;
import com.spring.boot.phoneshop.repositories.CategoryRepository;
import com.spring.boot.phoneshop.repositories.PhoneRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    public static final int COUNT_ITEMS_ON_PAGE = 10;
    @Resource
    PhoneRepository phoneRepository;
    @Resource
    CategoryRepository categoryRepository;

    @Override
    public Optional<Product> getById(Long key) {
        return phoneRepository.findById(key);
    }

    @Override
    public Long getCount(String category, String field, String order, String page) {
        long count;
        if (StringUtils.hasText(category) && categoryRepository.getCategoryByName(category) != null) {
            count = phoneRepository.countByCategory(categoryRepository.getCategoryByName(category));
        } else {
            count = phoneRepository.count();
        }
        return count;
    }
    @AddIdToModelName
    @IncreasePrice(value = 40)
    @Override
    public List<Product> getByParameters(String category, String field, String order, String page) {
        Integer pageNumber = getPage(page);
        Pageable sorted = getPageable(field, order, pageNumber);
        List<Product> products;
        if (StringUtils.hasText(category) && categoryRepository.getCategoryByName(category) != null) {
            products = phoneRepository.findAllByCategory(categoryRepository.getCategoryByName(category), sorted);
        } else {
            products = phoneRepository.findAll(sorted).getContent();
        }
        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    private Pageable getPageable(String field, String order, Integer pageNumber) {
        Pageable sorted;
        if (StringUtils.hasText(field)) {
            Sort sort = getSort(field, order);
            sorted = PageRequest.of(pageNumber, COUNT_ITEMS_ON_PAGE, sort);
        } else {
            sorted = PageRequest.of(pageNumber, COUNT_ITEMS_ON_PAGE);
        }
        return sorted;
    }

    private Sort getSort(String field, String order) {
        Sort sort = Sort.by(field).ascending();
        if (StringUtils.hasText(order)) {
            if (order.equalsIgnoreCase(Sort.Direction.DESC.toString())) {
                sort = Sort.by(field).descending();
            }
        }
        return sort;
    }

    private Integer getPage(String page) {
        int pageNumber = 0;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException ignored) {
        }
        return pageNumber;
    }
}