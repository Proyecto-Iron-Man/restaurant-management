package com.ironman.restaurantmanagement.expose.controller;


import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSavedDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @ApiResponse(responseCode = StatusCode.CREATED, description = "Product created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ProductSavedDto> create(@Valid @RequestBody ProductBodyDto productBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(productBody));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Product updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Product not found",
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
