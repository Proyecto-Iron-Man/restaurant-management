package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSavedDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.application.mapper.ProductMapper;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.presistence.entity.Product;
import com.ironman.restaurantmanagement.presistence.repository.ProductRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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
    public ProductSavedDto create(ProductBodyDto productBody) {
        Product product = productMapper.toEntity(productBody);
        product.setState(State.ENABLED.getValue());
        product.setCreatedAt(LocalDateTime.now());

        return productMapper.toSavedDto(productRepository.save(product));
    }

    @Override
    public ProductSavedDto update(Long id, ProductBodyDto productBody) throws DataNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> productDataNotFoundException(id));

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

    private DataNotFoundException productDataNotFoundException(Long id) {
        return new DataNotFoundException("Product not found with id: " + id);
    }
}
