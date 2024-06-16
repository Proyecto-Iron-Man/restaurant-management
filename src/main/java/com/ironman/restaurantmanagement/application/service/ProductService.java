package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.product.*;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;

import java.util.List;

public interface ProductService {

    List<ProductSmallDto> findAll();

    ProductDto findById(Long id) throws DataNotFoundException;

    ProductSavedDto create(ProductBodyDto productBody) throws DataNotFoundException;

    ProductSavedDto update(Long id, ProductBodyDto productBody) throws DataNotFoundException;

    ProductSavedDto disable(Long id) throws DataNotFoundException;

    PageResponse<ProductDto> paginatedSearch(ProductFilterDto filter);
}
