package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.category.*;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @ApiResponse(responseCode = StatusCode.OK, description = "List of all categories")
    @GetMapping
    public ResponseEntity<List<CategorySmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAll());
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Category by id")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findById(id));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by state")
    @GetMapping("/state/{state}")
    public ResponseEntity<List<CategorySmallDto>> findByState(@PathVariable("state") String state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findByState(state));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CategorySmallDto>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findByName(name));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by filters")
    @GetMapping("/filters")
    public ResponseEntity<List<CategorySmallDto>> findAllByFilters(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "state", required = false) String state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAllByFilters(name, state));
    }

    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories paginated")
    @GetMapping("/paginated")
    public ResponseEntity<Page<CategoryDto>> findAllPaginated(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAllPaginated(page, size));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories paginated by filters")
    @GetMapping("/paginated-search")
    public ResponseEntity<PageResponse<CategoryDto>> paginatedSearch(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "createdAtFrom", required = false) LocalDate createdAtFrom,
            @RequestParam(value = "createdAtTo", required = false) LocalDate createdAtTo,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortOrder", required = false) String sortOrder
    ) {
        CategoryFilterDto filter = CategoryFilterDto.builder()
                .page(page)
                .size(size)
                .name(name)
                .description(description)
                .state(state)
                .createdAtFrom(createdAtFrom)
                .createdAtTo(createdAtTo)
                .sortField(sortField)
                .sortOrder(sortOrder)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.paginatedSearch(filter));
    }

    @ApiResponse(responseCode = StatusCode.CREATED, description = "Category created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<CategorySavedDto> create(@Valid @RequestBody CategoryBodyDto categoryBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(categoryBody));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Category updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
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
    public ResponseEntity<CategorySavedDto> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoryBodyDto categoryBody) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.update(id, categoryBody));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Category disabled")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CategorySavedDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.disable(id));
    }

}
