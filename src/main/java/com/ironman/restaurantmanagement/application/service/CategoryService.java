package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;

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
}
