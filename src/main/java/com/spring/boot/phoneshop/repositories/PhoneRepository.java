package com.spring.boot.phoneshop.repositories;

import com.spring.boot.phoneshop.entities.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {
}
