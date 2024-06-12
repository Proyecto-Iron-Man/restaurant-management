package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {

    List<Category> findByStateOrderByIdDesc(String state);

    @Query(value = "SELECT c FROM Category AS c " +
            "WHERE UPPER(c.name) LIKE UPPER(CONCAT('%',:name, '%')) " +
            "ORDER BY c.id DESC"
    )
    List<Category> findByName(@Param("name") String name);

    @Query(value = "SELECT c FROM Category AS c " +
            "WHERE ( :#{#name} IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:name, '%')) ) " +
            "AND ( :#{#state} IS NULL OR UPPER(c.state) = UPPER(:state) ) "
    )
    List<Category> findAllByFilters(@Param("name") String name, @Param("state") String state);
}
