package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends ListCrudRepository<Product, Long> {

    @Query(value = "SELECT " +
            "p.id , p.name, p.description, p.price, p.stock, " +
            "p.category_id, c.name as category_name, " +
            "p.state, p.created_at, p.updated_at " +
            "FROM products p " +
            "INNER JOIN categories c ON c.id = p.category_id " +
            "WHERE ( :name IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%',:name, '%')) ) " +
            "AND ( :description IS NULL OR UPPER(p.description) LIKE UPPER(CONCAT('%',:description, '%')) ) " +
            "AND ( :categoryId IS NULL OR p.category_id = :categoryId ) " +
            "AND ( :stock IS NULL OR p.stock <= :stock ) " +
            "AND ( :state IS NULL OR UPPER(p.state) = UPPER(:state) ) " +
            "AND ( :createdAtFrom IS NULL OR DATE(p.created_at) >= TO_DATE(:createdAtFrom, 'YYYY-MM-DD') ) " +
            "AND ( :createdAtTo IS NULL OR DATE(p.created_at) <= TO_DATE(:createdAtTo, 'YYYY-MM-DD') ) "
            , nativeQuery = true
    )
    Page<Product> paginatedSearch(
            @Param("name") String name,
            @Param("description") String description,
            @Param("categoryId") Long categoryId,
            @Param("stock") Integer stock,
            @Param("state") String state,
            @Param("createdAtFrom") String createdAtFrom,
            @Param("createdAtTo") String createdAtTo,
            Pageable pageable
    );
}
