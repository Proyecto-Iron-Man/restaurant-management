package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategorySmallDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public CategorySavedDto create(CategoryBodyDto categoryBody) {
        Category category = categoryMapper.toEntity(categoryBody);
        category.setState(State.ENABLED.getValue());
        category.setCreatedAt(LocalDateTime.now());

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public CategorySavedDto update(Long id, CategoryBodyDto categoryBody) {
        Category category = categoryRepository.findById(id).get();

        categoryMapper.updateEntity(category, categoryBody);
        category.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public CategorySavedDto disable(Long id) {
        Category category = categoryRepository.findById(id).get();
        category.setState(State.DISABLED.getValue());

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public List<CategorySmallDto> findByState(String state) {
        return categoryRepository.findByStateOrderByIdDesc(state)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }
}
