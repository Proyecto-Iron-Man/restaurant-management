package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.DocumentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DocumentTypeRepository extends ListCrudRepository<DocumentType, Long> {

    List<DocumentType> findByStateOrderByIdDesc(String state);
}
