package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapperImpl;
import com.ironman.restaurantmanagement.application.service.impl.CategoryServiceImpl;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private StateMapperImpl stateMapper;

    @Spy
    private CategoryMapperImpl categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    Long id;
    Category category;

    @BeforeEach
    void setUp() {
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

        List<Field> categoryMapperFields = List.of(categoryMapper.getClass().getDeclaredFields());
        categoryMapperFields
                .stream()
                .filter(field -> "stateMapper".equals(field.getName()))
                .findFirst()
                .ifPresent(field ->{
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, categoryMapper, stateMapper);
                });

    }


    @Test
    void findAll() {
        // Given
        int expectedSize = 1;

        when(categoryRepository.findAll())
                .thenReturn(List.of(category));

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
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        // When
        CategoryDto categoryFound = categoryService.findById(id);

        // Then
        assertNotNull(categoryFound);
        assertEquals(category.getId(), categoryFound.getId());
        assertEquals(category.getName(), categoryFound.getName());
        assertEquals(category.getDescription(), categoryFound.getDescription());
        assertEquals(category.getUrlKey(), categoryFound.getUrlKey());
        assertEquals(category.getState(), categoryFound.getState().getValue());
    }

}