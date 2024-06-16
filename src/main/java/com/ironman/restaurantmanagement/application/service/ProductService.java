package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSavedDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;

import java.util.List;

public interface ProductService {

    List<ProductSmallDto> findAll();

    ProductDto findById(Long id) throws DataNotFoundException;

    ProductSavedDto create(ProductBodyDto productBody);

    ProductSavedDto update(Long id, ProductBodyDto productBody) throws DataNotFoundException;

    ProductSavedDto disable(Long id) throws DataNotFoundException;

}
