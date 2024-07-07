package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<Customer, Long> {

    @Query(value = "SELECT " +
            " c.id, c.name, c.last_name, c.document_type_id, c.document_number " +
            " , c.phone_number, c.address ,c.state, c.created_at, c.updated_at " +
            " ,d.name as document_type_name " +
            " FROM customers c " +
            " INNER JOIN document_types d ON d.id = c.document_type_id " +
            " WHERE (:name IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:name,'%')) )" +
            " AND (:lastName IS NULL OR UPPER(c.last_name) LIKE UPPER(CONCAT('%',:lastName,'%')) )" +
            " AND (:documentTypeId IS NULL OR c.document_type_id = :documentTypeId)" +
            " AND (:documentNumber IS NULL OR c.document_number LIKE CONCAT('%',:documentNumber,'%'))" +
            " AND (:state IS NULL OR UPPER(c.state) = UPPER(:state) )" +
            " AND (:createdAtFrom IS NULL OR DATE(c.created_at) >= TO_DATE(:createdAtFrom, 'YYYY-MM-DD') )" +
            " AND (:createdAtTo IS NULL OR DATE(c.created_at) <= TO_DATE(:createdAtTo, 'YYYY-MM-DD') )",
            nativeQuery = true)
    Page<Customer> paginatedSearch(
            @Param("name") String name,
            @Param("lastName") String lastName,
            @Param("documentTypeId") Long documentTypeId,
            @Param("documentNumber") String documentNumber,
            @Param("state") String state,
            @Param("createdAtFrom") String createdAtFrom,
            @Param("createdAtTo") String createdAtTo,
            Pageable pageable
    );
}
