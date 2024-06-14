package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.*;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();

    CategoryDto findById(Long id) throws DataNotFoundException;

    CategorySavedDto create(CategoryBodyDto categoryBody);

    CategorySavedDto update(Long id, CategoryBodyDto categoryBody) throws DataNotFoundException;

    CategorySavedDto disable(Long id) throws DataNotFoundException;

    List<CategorySmallDto> findByState(String state);

    List<CategorySmallDto> findByName(String name);

    List<CategorySmallDto> findAllByFilters(String name, String state);

    PageResponse<CategoryDto> findAllPaginated(int page, int size);

    PageResponse<CategoryDto> paginatedSearch(CategoryFilterDto filter);
}
