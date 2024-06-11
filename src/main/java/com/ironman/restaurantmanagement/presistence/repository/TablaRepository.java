package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Tabla;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface TablaRepository extends ListCrudRepository<Tabla, Long> {

    // JPA Query Methods
    List<Tabla> findByStateIgnoreCaseOrderByIdDesc(String state);
}
