package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.DocumentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentTypeRepository extends ListCrudRepository<DocumentType, Long> {

    List<DocumentType> findByStateOrderByIdDesc(String state);

    @Query(value = "SELECT dt FROM DocumentType AS dt "+
            "WHERE UPPER(dt.name) LIKE UPPER(CONCAT('%', :name, '%')) ORDER BY dt.id DESC")
    List<DocumentType> findByName(@Param("name") String name);
}
