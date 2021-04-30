package com.spring.boot.phoneshop.repositories;

import com.spring.boot.phoneshop.entities.Notebook;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookRepository extends PagingAndSortingRepository<Notebook, Long> {
}
