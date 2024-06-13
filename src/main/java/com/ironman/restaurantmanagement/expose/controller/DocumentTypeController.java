package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeBodyDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSavedDto;
import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSmallDto;
import com.ironman.restaurantmanagement.application.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @GetMapping
    public List<DocumentTypeSmallDto> findAll() {
        return documentTypeService.findAll();
    }

    @GetMapping("/{id}")
    public DocumentTypeDto findById(@PathVariable("id") Long id) {
        return documentTypeService.findById(id);
    }

    @PostMapping
    public DocumentTypeSavedDto create(@RequestBody DocumentTypeBodyDto documentTypeBodyDto) {
        return documentTypeService.create(documentTypeBodyDto);
    }

    @PutMapping("/{id}")
    public DocumentTypeSavedDto update(@PathVariable("id") Long id, @RequestBody DocumentTypeBodyDto documentTypeBodyDto) {
        return documentTypeService.update(id, documentTypeBodyDto);
    }

    @DeleteMapping("/{id}")
    public DocumentTypeSavedDto disable(@PathVariable("id") Long id) {
        return documentTypeService.disable(id);
    }

    @GetMapping("/state/{state}")
    public List<DocumentTypeSmallDto> findByStateOrderByIdDesc(@PathVariable("state") String state) {
        return documentTypeService.findByStateOrderByIdDesc(state);
    }

    @GetMapping("/name/{name}")
    public List<DocumentTypeSmallDto> findByName(@PathVariable("name") String name) {
        return documentTypeService.findByName(name);
    }
    @GetMapping("/filters")
    public List<DocumentTypeSmallDto> findAllByFilters(@RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "state", required = false) String state) {
        return documentTypeService.findAllByFilters(name, state);
    }
}
