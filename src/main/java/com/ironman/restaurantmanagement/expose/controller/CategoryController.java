package com.ironman.restaurantmanagement.expose.controller;


import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> findAll() {

        List<Category> categories = (List<Category>) categoryRepository.findAll();

        return categories;
    }

    @GetMapping("/{id}")
    public Category findById( @PathVariable("id") Long id) {

        Category category = categoryRepository.findById(id).orElse(null);

        return category;
    }


}
