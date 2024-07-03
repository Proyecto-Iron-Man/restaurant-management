package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.customer.CustomerBodyDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerSavedDto;
import com.ironman.restaurantmanagement.application.dto.customer.CustomerSmallDto;
import com.ironman.restaurantmanagement.presistence.entity.Customer;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

// MapStruct annotations
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, DocumentTypeMapper.class}
)
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);

    CustomerSmallDto toSmallDto(Customer customer);

    CustomerSavedDto toSavedDto(Customer customer);

    Customer toEntity(CustomerBodyDto customerBody);

    void updateEntity(@MappingTarget Customer customer, CustomerBodyDto customerBody);
}
