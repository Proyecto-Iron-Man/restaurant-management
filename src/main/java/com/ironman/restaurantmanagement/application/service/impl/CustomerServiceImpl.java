package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.customer.*;
import com.ironman.restaurantmanagement.application.mapper.CustomerMapper;
import com.ironman.restaurantmanagement.application.service.CustomerService;
import com.ironman.restaurantmanagement.presistence.entity.Customer;
import com.ironman.restaurantmanagement.presistence.enums.CustomerSortField;
import com.ironman.restaurantmanagement.presistence.repository.CustomerRepository;
import com.ironman.restaurantmanagement.presistence.repository.DocumentTypeRepository;
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
public class CustomerServiceImpl extends PagingAndSortingBuilder implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final DocumentTypeRepository documentTypeRepository;


    @Override
    public List<CustomerSmallDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toSmallDto)
                .toList();
    }

    @Override
    public CustomerDto findById(Long id) throws DataNotFoundException {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> customerDataNotFoundException(id));
    }

    @Override
    public CustomerSavedDto create(CustomerBodyDto customerBody) throws DataNotFoundException {
        documentTypeRepository.findById(customerBody.getDocumentTypeId())
                .orElseThrow(() -> documentTypeDataNotFoundException(customerBody.getDocumentTypeId()));

        Customer customer = customerMapper.toEntity(customerBody);
        customer.setState(State.ENABLED.getValue());
        customer.setCreatedAt(LocalDateTime.now());

        return customerMapper.toSavedDto(customerRepository.save(customer));
    }

    @Override
    public CustomerSavedDto update(Long id, CustomerBodyDto customerBody) throws DataNotFoundException {
        documentTypeRepository.findById(customerBody.getDocumentTypeId())
                .orElseThrow(() -> documentTypeDataNotFoundException(customerBody.getDocumentTypeId()));

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> customerDataNotFoundException(id));

        customerMapper.updateEntity(customer, customerBody);
        customer.setUpdatedAt(LocalDateTime.now());

        return customerMapper.toSavedDto(customerRepository.save(customer));
    }

    @Override
    public CustomerSavedDto disable(Long id) throws DataNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> customerDataNotFoundException(id));

        customer.setState(State.DISABLED.getValue());

        return customerMapper.toSavedDto(customerRepository.save(customer));
    }

    @Override
    public PageResponse<CustomerDto> paginatedSearch(CustomerFilterDto filter) {
        // Variables
        String sortColumn = CustomerSortField.getSqlColumn(filter.getSortField());
        Pageable pageable = buildPageable(filter, sortColumn);

        var pageCustomer = customerRepository.paginatedSearch(
                filter.getName(),
                filter.getLastName(),
                filter.getDocumentTypeId(),
                filter.getDocumentNumber(),
                filter.getState(),
                localDateToString(filter.getCreatedAtFrom()),
                localDateToString(filter.getCreatedAtTo()),
                pageable
        );

        return buildPageResponse(pageCustomer, customerMapper::toDto);
    }

    private DataNotFoundException customerDataNotFoundException(Long id) {
        return new DataNotFoundException("Customer not found with id: " + id);
    }

    private DataNotFoundException documentTypeDataNotFoundException(Long documentTypeId) {
        return new DataNotFoundException("Document type not found with id: " + documentTypeId);
    }
}
