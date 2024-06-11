package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Tabla;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TablaRepository extends ListCrudRepository<Tabla, Long> {

    // JPA Query Methods (Derived Query Methods)
    List<Tabla> findByStateIgnoreCaseOrderByIdDesc(String state);

    // JPQL, La tabla sera el nombre del entity
    @Query(value = "SELECT t FROM  Tabla AS t " +
            "WHERE UPPER(t.name) LIKE UPPER(CONCAT('%', :name ,'%')) " +
            "ORDER BY t.id DESC")
    List<Tabla> findByName(@Param("name") String name);

}
