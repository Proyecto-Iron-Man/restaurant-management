package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeBodyDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSavedDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSmallDto;
import com.ironman.restaurantmanagement.application.mapper.DocumentTypeMapper;
import com.ironman.restaurantmanagement.application.service.DocumentTypeService;
import com.ironman.restaurantmanagement.presistence.entity.DocumentType;
import com.ironman.restaurantmanagement.presistence.repository.DocumentTypeRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public List<DocumentTypeSmallDto> findAll() {
        return (documentTypeRepository.findAll())
                .stream()
                .map(documentTypeMapper::toSmallDto)
                .toList();
    }

    @Override
    public DocumentTypeDto findById(Long id) {
        return documentTypeRepository.findById(id)
                .map(documentTypeMapper::toDto)
                .orElse(null);
    }

    @Override
    public DocumentTypeSavedDto create(DocumentTypeBodyDto documentTypeBodyDto) {
        DocumentType documentType = documentTypeMapper.toEntity(documentTypeBodyDto);
        documentType.setState(State.ENABLED.getValue());
        documentType.setCreatedAt(LocalDateTime.now());
        return documentTypeMapper.toSavedDto(documentTypeRepository.save(documentType));
    }

    @Override
    public DocumentTypeSavedDto update(Long id, DocumentTypeBodyDto documentTypeBodyDto) {
        DocumentType documentType = documentTypeRepository.findById(id).get();
        documentTypeMapper.updateEntity(documentType, documentTypeBodyDto);
        documentType.setUpdatedAt(LocalDateTime.now());
        return documentTypeMapper.toSavedDto(documentTypeRepository.save(documentType));
    }

    @Override
    public DocumentTypeSavedDto disable(Long id) {
        DocumentType documentType = documentTypeRepository.findById(id).get();
        documentType.setState(State.DISABLED.getValue());
        return documentTypeMapper.toSavedDto(documentTypeRepository.save(documentType));
    }

    @Override
    public List<DocumentTypeSmallDto> findByStateOrderByIdDesc(String state) {
        return documentTypeRepository.findByStateOrderByIdDesc(state)
                .stream()
                .map(documentTypeMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<DocumentTypeSmallDto> findByName(String name) {
        return documentTypeRepository.findByName(name)
                .stream()
                .map(documentTypeMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<DocumentTypeSmallDto> findAllByFilters(String name, String state) {
        return documentTypeRepository.findAllByFilters(name, state)
                .stream()
                .map(documentTypeMapper::toSmallDto)
                .toList();
    }
}
