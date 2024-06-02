package com.ironman.restaurantmanagement.expose.controller;


import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById( @PathVariable("id") Long id) {
        return categoryService.findById(id);
    }


}
