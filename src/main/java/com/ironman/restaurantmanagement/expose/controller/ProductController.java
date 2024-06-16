package com.ironman.restaurantmanagement.expose.controller;


import com.ironman.restaurantmanagement.application.dto.product.*;
import com.ironman.restaurantmanagement.application.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    @ApiResponse(responseCode = StatusCode.OK, description = "List of all products")
    @GetMapping
    public ResponseEntity<List<ProductSmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAll());
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Product by id")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Product not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findById(id));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of products with pagination")
    @GetMapping("paginated-search")
    public ResponseEntity<PageResponse<ProductDto>> paginatedSearch(
            @NotNull(message = "Page number is required")
            @Min(value = 1, message = "Page number must be positive")
            @RequestParam(name = "page", defaultValue = "1") int page,

            @NotNull(message = "Page size is required")
            @Min(value = 1, message = "Page size must be positive")
            @RequestParam(name = "size", defaultValue = "10") int size,

            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,

            @Parameter(description = "Category id must be a positive number")
            @PositiveOrZero(message = "Category id must be a positive number")
            @RequestParam(name = "categoryId", required = false) Long categoryId,

            @Parameter(description = "Stock must be a positive number")
            @PositiveOrZero(message = "Stock must be a positive number")
            @RequestParam(name = "stock", required = false) Integer stock,

            @Parameter(description = "State must be 'A' for enabled or 'E' for disabled")
            @Pattern(regexp = "^[AE]$", message = "State must be 'A' for enabled or 'E' for disabled")
            @RequestParam(value = "state", required = false) String state,

            @RequestParam(value = "createdAtFrom", required = false) LocalDate createdAtFrom,
            @RequestParam(value = "createdAtTo", required = false) LocalDate createdAtTo,

            @Parameter(description = "Sort field must be 'id', 'name', 'categoryId', 'stock', 'state' or 'createdAt' (default: 'id')")
            @Pattern(regexp = "^(id|name|categoryId|stock|state|createdAt)$", message = "Sort field must be 'id', 'name', 'categoryId', 'stock', 'state' or 'createdAt' (default: 'id')")
            @RequestParam(value = "sortField", required = false) String sortField,

            @Parameter(description = "Sort order must be 'ASC' or 'DESC' (default: 'DESC')")
            @Pattern(regexp = "^(ASC|DESC)$", message = "Sort order must be 'ASC' or 'DESC' (default: 'DESC')")
            @RequestParam(value = "sortOrder", required = false) String sortOrder

    ) {
        var filter = ProductFilterDto.builder()
                .page(page)
                .size(size)
                .name(name)
                .description(description)
                .categoryId(categoryId)
                .stock(stock)
                .state(state)
                .createdAtFrom(createdAtFrom)
                .createdAtTo(createdAtTo)
                .sortField(sortField)
                .sortOrder(sortOrder)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.paginatedSearch(filter));
    }


    @ApiResponse(responseCode = StatusCode.CREATED, description = "Product created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ProductSavedDto> create(@Valid @RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(productBody));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Product updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Product or Category not found",
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
    @PutMapping("/{id}")
    public ResponseEntity<ProductSavedDto> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.update(id, productBody));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Product disabled")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Product not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSavedDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.disable(id));
    }
}
