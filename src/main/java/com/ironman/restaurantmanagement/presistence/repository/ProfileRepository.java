package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProfileRepository extends ListCrudRepository<Profile, Long> {

    List<Profile> findByStateOrderByIdDesc(String state);

    @Query(value = "SELECT p FROM Profile AS p " +
            "WHERE UPPER(p.name) LIKE UPPER(CONCAT('%',:name, '%')) " +
            "ORDER BY p.id DESC"
    )
    List<Profile> findByName(@Param("name") String name);

    @Query(value = "SELECT p FROM Profile AS p " +
            "WHERE ( :#{#name} IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%',:name, '%')) ) " +
            "AND ( :#{#state} IS NULL OR UPPER(p.state) = UPPER(:state) ) "
    )
    List<Profile> findAllByFilters(@Param("name") String name, @Param("state") String state);
}
