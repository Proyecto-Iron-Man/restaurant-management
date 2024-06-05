package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;

import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();
    CategoryDto findById(Long id);
}
