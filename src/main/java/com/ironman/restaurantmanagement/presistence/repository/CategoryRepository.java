package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends
        ListCrudRepository<Category, Long>,
        ListPagingAndSortingRepository<Category, Long> {

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

    @Query(value = "SELECT " +
            "c.id, c.name, c.description, c.url_key, c.state, c.created_at, c.updated_at " +
            "FROM categories c " +
            "WHERE ( :name IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:name, '%')) ) " +
            "AND ( :description IS NULL OR UPPER(c.description) LIKE UPPER(CONCAT('%',:description, '%')) ) " +
            "AND ( :state IS NULL OR UPPER(c.state) = UPPER(:state) ) " +
            "AND ( :createdAtFrom IS NULL OR DATE(c.created_at) >= TO_DATE(:createdAtFrom, 'YYYY-MM-DD') ) " +
            "AND ( :createdAtTo IS NULL OR DATE(c.created_at) <= TO_DATE(:createdAtTo, 'YYYY-MM-DD') ) "
            , nativeQuery = true
    )
    Page<Category> paginatedSearch(
            @Param("name") String name,
            @Param("description") String description,
            @Param("state") String state,
            @Param("createdAtFrom") String createdAtFrom,
            @Param("createdAtTo") String createdAtTo,
            Pageable pageable
    );
}
