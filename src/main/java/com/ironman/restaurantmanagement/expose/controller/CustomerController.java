package com.ironman.restaurantmanagement.expose.controller;


import com.ironman.restaurantmanagement.application.dto.customer.*;
import com.ironman.restaurantmanagement.application.service.CustomerService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService clientService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<CustomerSmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.findAll());
    }


    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id)
            throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.findById(id));
    }


    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/paginated-search")
    public ResponseEntity<PageResponse<CustomerDto>> paginatedSearch(
            @NotNull(message = "El campo page es requerido")
            @Min(value = 1, message = "El número de página debe ser positivo")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @NotNull(message = "El campo size es requerido")
            @Min(value = 1, message = "El tamaño de la página debe ser positivo")
            @RequestParam(name = "size", defaultValue = "10") int size,

            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "lastName", required = false) String lastName,

            @Parameter(description = "DocumentType id must be a positive number")
            @PositiveOrZero(message = "DocumentType id must be a positive number")
            @RequestParam(name = "documentTypeId", required = false) Long documentTypeId,

            @RequestParam(name = "documentNumber", required = false) String documentNumber,

            @Parameter(description = "El estado debe ser 'A' o 'E'")
            @Pattern(regexp = "^[AE]$", message = "El estado debe ser 'A' o 'E'")
            @RequestParam(name = "state", required = false) String state,

            @Parameter(description = "El campo createdAtFrom debe estar en el formato yyyy-MM-dd")
            @RequestParam(value = "createdAtFrom", required = false) LocalDate createdAtFrom,

            @Parameter(description = "El campo createdAtTo debe estar en el formato yyyy-MM-dd")
            @RequestParam(value = "createdAtTo", required = false) LocalDate createdAtTo,

            @Parameter(description = "El campo Sort field debe ser 'id', 'name', lastName, documentTypeId o 'createdAt'")
            @Pattern(regexp = "^(id|name|lastName|documentTypeId|createdAt)$", message = "El campo Sort field debe ser id, 'name', lastName, documentTypeId o 'createdAt'")
            @RequestParam(value = "sortField", required = false) String sortField,

            @Parameter(description = "Sort order must be 'ASC' or 'DESC' (default: 'DESC')")
            @Pattern(regexp = "^(ASC|DESC)$", message = "Sort order must be 'ASC' or 'DESC' (default: 'DESC')")
            @RequestParam(value = "sortOrder", required = false) String sortOrder
    ) {
        CustomerFilterDto filter = CustomerFilterDto.builder()
                .page(page)
                .size(size)
                .name(name)
                .lastName(lastName)
                .documentTypeId(documentTypeId)
                .documentNumber(documentNumber)
                .state(state)
                .createdAtFrom(createdAtFrom)
                .createdAtTo(createdAtTo)
                .sortField(sortField)
                .sortOrder(sortOrder)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.paginatedSearch(filter));
    }


    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<CustomerSavedDto> create(@Valid @RequestBody CustomerBodyDto clientBody)
            throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.create(clientBody));
    }


    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<CustomerSavedDto> update(@PathVariable("id") Long id, @Valid @RequestBody CustomerBodyDto clientBody)
            throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.update(id, clientBody));
    }


    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerSavedDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.disable(id));
    }

}
