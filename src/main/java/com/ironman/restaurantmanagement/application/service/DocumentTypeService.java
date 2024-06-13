package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeBodyDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSavedDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSmallDto;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentTypeSmallDto> findAll();
    DocumentTypeDto findById(Long id);
    DocumentTypeSavedDto create(DocumentTypeBodyDto documentTypeBodyDto);
    DocumentTypeSavedDto update(Long id, DocumentTypeBodyDto documentTypeBodyDto);
    DocumentTypeSavedDto disable(Long id);
    List<DocumentTypeSmallDto> findByStateOrderByIdDesc(String state);
    List<DocumentTypeSmallDto> findByName(String name);
    List<DocumentTypeSmallDto> findAllByFilters(String name, String state);
}
