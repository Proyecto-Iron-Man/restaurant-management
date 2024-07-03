package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.customer.CustomerBodyDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerFilterDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerSavedDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerSmallDto;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerSmallDto> findAll();

    CustomerDto findById(Long id) throws DataNotFoundException;

    CustomerSavedDto create(CustomerBodyDto customerBody) throws DataNotFoundException;

    CustomerSavedDto update(Long id, CustomerBodyDto customerBody) throws DataNotFoundException;

    CustomerSavedDto disable(Long id) throws DataNotFoundException;

    PageResponse<CustomerDto> paginatedSearch(CustomerFilterDto filter);
}
