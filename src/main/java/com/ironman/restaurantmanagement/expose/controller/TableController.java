package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;
import com.ironman.restaurantmanagement.application.service.TablaService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @ApiResponse(responseCode = StatusCode.OK, description = "Return a list of all tables")
    @GetMapping
    public ResponseEntity<List<TablaSmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.findAll());
    }


    @Operation(summary = "Mostrar las tablas por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Table by id")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Table not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<TablaDto> findById(@PathVariable Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.findById(id));
    }


    @Operation(summary = "Mostar las tablas por el estado, desendentemente")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of tables by state")
    @GetMapping("/state/{state}")
    public ResponseEntity<List<TablaSmallDto>> findByStateOrderByIdDescIgnoreCase(@PathVariable String state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.findByStateIgnoreCaseOrderByIdDesc(state));
    }


    @Operation(summary = "Mostar las tablas por el nombre, desendentemente")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of tables by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<TablaSmallDto>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.findByName(name));
    }


    @Operation(summary = "Mostar las tablas por los filtros de nombre y estado")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of tables by filters")
    @GetMapping("/filters")
    public ResponseEntity<List<TablaSmallDto>> findAllFilters(@RequestParam(value = "name", required = false) String name,
                                                              @RequestParam(value = "state", required = false) String state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.findAllByFilters(name, state));
    }


    @Operation(summary = "Agregar una tabla")
    @ApiResponse(responseCode = StatusCode.OK, description = "Table created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<TablaSaveDto> create(@Valid @RequestBody TablaBodyDto tablaBodyDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tablaService.create(tablaBodyDto));
    }


    @Operation(summary = "Actualizar una tabla")
    @ApiResponse(responseCode = StatusCode.OK, description = "Table updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Table not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PutMapping("{id}")
    public ResponseEntity<TablaSaveDto> update(@PathVariable Long id, @Valid @RequestBody TablaBodyDto tablaBodyDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.update(id, tablaBodyDto));
    }


    @Operation(summary = "Eliminar una tabla por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Table disabled")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Table not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<TablaSaveDto> disable(@PathVariable Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tablaService.disable(id));
    }

}
