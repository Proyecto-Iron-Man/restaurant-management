package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;
import com.ironman.restaurantmanagement.application.service.TablaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotations
@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private final TablaService tablaService;

    @Operation(summary = "Mostrar todas las tablas")
    @GetMapping
    public List<TablaSmallDto> findAll() {
        return tablaService.findAll();
    }

    @Operation(summary = "Mostrar las tablas por el id")
    @GetMapping("/{id}")
    public TablaDto findById(@PathVariable Long id) {
        return tablaService.findById(id);
    }

    @Operation(summary = "Mostar las tablas por el estado, desendentemente")
    @GetMapping("/state/{state}")
    public List<TablaSmallDto> findByStateOrderByIdDescIgnoreCase(@PathVariable String state) {
        return tablaService.findByStateIgnoreCaseOrderByIdDesc(state);
    }

    @Operation(summary = "Agregar una tabla")
    @PostMapping
    public TablaSaveDto create(@RequestBody TablaBodyDto tablaBodyDto) {
        return tablaService.create(tablaBodyDto);
    }

    @Operation(summary = "Actualizar una tabla")
    @PutMapping("{id}")
    public TablaSaveDto update(@PathVariable Long id, @RequestBody TablaBodyDto tablaBodyDto) {
        return tablaService.update(id, tablaBodyDto);
    }

    @Operation(summary = "Eliminar una tabla por el id")
    @DeleteMapping("/{id}")
    public TablaSaveDto disable(@PathVariable Long id) {
        return tablaService.disable(id);
    }

}
