package com.ironman.restaurantmanagement.application.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBodyDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 300, message = "Name must be between 3 and 300 characters")
    private String name;

    @Size(max = 4000, message = "Description must be less than 4000 characters")
    private String description;

    @PositiveOrZero(message = "Price must be positive")
    private BigDecimal price;

    @PositiveOrZero(message = "Stock must be positive")
    private Integer stock;

    @NotNull(message = "CategoryId is required")
    @Positive(message = "Minimum stock must be positive")
    private Long categoryId;
}
