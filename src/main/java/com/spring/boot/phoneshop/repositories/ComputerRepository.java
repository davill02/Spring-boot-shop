package com.spring.boot.phoneshop.repositories;

import com.spring.boot.phoneshop.entities.Computer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {
}
