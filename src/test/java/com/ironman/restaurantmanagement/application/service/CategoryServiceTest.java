package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySavedDto;
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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
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

    @Captor
    private ArgumentCaptor<Category> categoryArgumentCaptor;

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

    @Test
    void create() {
        // Given
        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation -> {
                    Category categoryCreated = invocation.getArgument(0);
                    categoryCreated.setId(id);

                    return categoryCreated;
                });

        CategoryBodyDto categoryBody = CategoryBodyDto.builder()
                .name("Pizza Mozarella")
                .description("Pizza Gourmet")
                .build();

        // When
        CategorySavedDto categorySaved = categoryService.create(categoryBody);

        // Then
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category capturedCategory = categoryArgumentCaptor.getValue();

        assertNotNull(categorySaved);
        assertNotNull(categorySaved.getId());
        assertEquals(category.getState(), categorySaved.getState().getValue());

        assertNotNull(capturedCategory);
        assertEquals(categoryBody.getName(), capturedCategory.getName());
        assertEquals(categoryBody.getDescription(), capturedCategory.getDescription());
    }

    @Test
    void update() throws DataNotFoundException {
        // Given
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CategoryBodyDto categoryBody = CategoryBodyDto.builder()
                .name("Pizza Mozarella")
                .description("Pizza Gourmet")
                .build();

        // When
        CategorySavedDto categorySaved = categoryService.update(id, categoryBody);

        // Then
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category capturedCategory = categoryArgumentCaptor.getValue();

        assertNotNull(categorySaved);
        assertEquals(category.getId(), categorySaved.getId());
        assertEquals(category.getState(), categorySaved.getState().getValue());

        assertNotNull(capturedCategory);
        assertEquals(categoryBody.getName(), capturedCategory.getName());
        assertEquals(categoryBody.getDescription(), capturedCategory.getDescription());
    }

    @Test
    void disable() throws DataNotFoundException {
        // Given
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CategorySavedDto categorySaved = categoryService.disable(id);

        // Then
        assertNotNull(categorySaved);
        assertEquals(category.getId(), categorySaved.getId());
        assertEquals(State.DISABLED.getValue(), categorySaved.getState().getValue());
    }

}