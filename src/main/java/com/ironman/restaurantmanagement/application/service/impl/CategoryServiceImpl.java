package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.category.*;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.enums.CategorySortField;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import com.ironman.restaurantmanagement.shared.page.PagingAndSortingBuilder;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ironman.restaurantmanagement.shared.util.DateHelper.localDateToString;
import static com.ironman.restaurantmanagement.shared.util.StringHelper.buildSlugsKeywords;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@Service
public class CategoryServiceImpl extends PagingAndSortingBuilder implements CategoryService {

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
    public CategoryDto findById(Long id) throws DataNotFoundException {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> categoryDataNotFoundException(id));
    }

    @Override
    public CategorySavedDto create(CategoryBodyDto categoryBody) {
        Category category = categoryMapper.toEntity(categoryBody);
        category.setUrlKey(buildSlugsKeywords(category.getName()));
        category.setState(State.ENABLED.getValue());
        category.setCreatedAt(LocalDateTime.now());

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public CategorySavedDto update(Long id, CategoryBodyDto categoryBody) throws DataNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> categoryDataNotFoundException(id));

        categoryMapper.updateEntity(category, categoryBody);
        category.setUrlKey(buildSlugsKeywords(category.getName()));
        category.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public CategorySavedDto disable(Long id) throws DataNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> categoryDataNotFoundException(id));

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

    @Override
    public List<CategorySmallDto> findByName(String name) {
        return categoryRepository.findByName(name)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<CategorySmallDto> findAllByFilters(String name, String state) {
        return categoryRepository.findAllByFilters(name, state)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public PageResponse<CategoryDto> findAllPaginated(int page, int size) {
        // Variables
        Pageable pageable = PageRequest.of(page - 1, size);

        // Process
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        // Result
        return buildPageResponse(categoryPage, categoryMapper::toDto);
    }

    @Override
    public PageResponse<CategoryDto> paginatedSearch(CategoryFilterDto filter) {
        // Variables
        String column = CategorySortField.getSqlColumn(filter.getSortField());
        Pageable pageable = buildPageable(filter, column);

        // Process
        Page<Category> categoryPage = categoryRepository.paginatedSearch(
                filter.getName(),
                filter.getDescription(),
                filter.getState(),
                localDateToString(filter.getCreatedAtFrom()),
                localDateToString(filter.getCreatedAtTo()),
                pageable
        );

        // Result
        return buildPageResponse(categoryPage, categoryMapper::toDto);
    }

    private DataNotFoundException categoryDataNotFoundException(Long id) {
        return new DataNotFoundException("Category not found with id: " + id);
    }
}
