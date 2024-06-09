package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeBodyDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSavedDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSmallDto;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.entity.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentTypeMapper {
    DocumentTypeDto toDto(DocumentType documentType);
    DocumentTypeSmallDto toSmallDto(DocumentType documentType);
    DocumentTypeSavedDto toSavedDto(DocumentType documentType);
    DocumentType toEntity(DocumentTypeBodyDto documentTypeBodyDto);
    DocumentType updateEntity(@MappingTarget DocumentType documentType, DocumentTypeBodyDto documentTypeBodyDto);
}
