package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.product.*;
import com.ironman.restaurantmanagement.application.mapper.ProductMapper;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.presistence.entity.Product;
import com.ironman.restaurantmanagement.presistence.enums.ProductSortField;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.presistence.repository.ProductRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import com.ironman.restaurantmanagement.shared.page.PagingAndSortingBuilder;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ironman.restaurantmanagement.shared.util.DateHelper.localDateToString;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@Service
public class ProductServiceImpl extends PagingAndSortingBuilder implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductSmallDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toSmallDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) throws DataNotFoundException {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> productDataNotFoundException(id));
    }

    @Override
    public ProductSavedDto create(ProductBodyDto productBody) throws DataNotFoundException {
        // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                .orElseThrow(() -> categoryDataNotFoundException(productBody));

        Product product = productMapper.toEntity(productBody);
        product.setState(State.ENABLED.getValue());
        product.setCreatedAt(LocalDateTime.now());

        return productMapper.toSavedDto(productRepository.save(product));
    }

    @Override
    public ProductSavedDto update(Long id, ProductBodyDto productBody) throws DataNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> productDataNotFoundException(id));

        // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                .orElseThrow(() -> categoryDataNotFoundException(productBody));

        productMapper.updateEntity(product, productBody);

        return productMapper.toSavedDto(productRepository.save(product));
    }

    @Override
    public ProductSavedDto disable(Long id) throws DataNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> productDataNotFoundException(id));

        product.setState(State.DISABLED.getValue());

        return productMapper.toSavedDto(productRepository.save(product));
    }

    @Override
    public PageResponse<ProductDto> paginatedSearch(ProductFilterDto filter) {
        // Variables
        String sortColumn = ProductSortField.getSqlColumn(filter.getSortField());
        Pageable pageable = buildPageable(filter, sortColumn);

        // Process
        var pageProduct = productRepository.paginatedSearch(
                filter.getName(),
                filter.getDescription(),
                filter.getCategoryId(),
                filter.getStock(),
                filter.getState(),
                localDateToString(filter.getCreatedAtFrom()),
                localDateToString(filter.getCreatedAtTo()),
                pageable
        );

        // Result
        return buildPageResponse(pageProduct, productMapper::toDto);
    }

    private DataNotFoundException productDataNotFoundException(Long id) {
        return new DataNotFoundException("Product not found with id: " + id);
    }

    private DataNotFoundException categoryDataNotFoundException(ProductBodyDto productBody) {
        return new DataNotFoundException("Category not found with id: " + productBody.getCategoryId());
    }
}
