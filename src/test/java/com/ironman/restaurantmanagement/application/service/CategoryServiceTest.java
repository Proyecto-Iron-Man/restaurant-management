package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagement.application.service.impl.CategoryServiceImpl;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private CategoryService categoryService;

    Long id;
    Category category;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);

        id = 1L;
        category = Category.builder()
                .id(id)
                .name("Pizza")
                .description("Pizza description")
                .urlKey("pizza")
                .state(State.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();



    }


    @Test
    void findAll() {
        // Given
        int expectedSize = 1;

        when(categoryRepository.findAll())
                .thenReturn(List.of(category));

        CategorySmallDto categorySmallDto = CategorySmallDto.builder()
                .id(id)
                .name(category.getName())
                .build();

        when(categoryMapper.toSmallDto(category))
                .thenReturn(categorySmallDto);

        // When

        List<CategorySmallDto> categories = categoryService.findAll();

        // Then
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertEquals(expectedSize, categories.size());
    }


    @Test
    void findById() throws DataNotFoundException {
        // Given
        when(categoryRepository.findById(id))
                .thenReturn(Optional.of(category));

        CategoryDto categoryDto = CategoryDto.builder()
                .id(id)
                .name(category.getName())
                .description(category.getDescription())
                .urlKey(category.getUrlKey())
                .state(State.ENABLED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(categoryMapper.toDto(category))
                .thenReturn(categoryDto);

        // When
        CategoryDto categoryFound = categoryService.findById(id);

        // Then
        assertNotNull(categoryFound);
        assertEquals(1L, categoryFound.getId());
        assertEquals(category.getName(), categoryFound.getName());

    }

}