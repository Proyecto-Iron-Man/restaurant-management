package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.presistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
}
