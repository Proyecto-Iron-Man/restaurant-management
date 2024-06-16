package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSavedDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.presistence.entity.Product;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

// MapStruct annotations
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, CategoryMapper.class}
)
public interface ProductMapper {

    ProductDto toDto(Product product);

    ProductSmallDto toSmallDto(Product product);

    ProductSavedDto toSavedDto(Product product);

    Product toEntity(ProductBodyDto productBody);

    void updateEntity(@MappingTarget Product product, ProductBodyDto productBody);

}
