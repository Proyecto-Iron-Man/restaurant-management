package com.ironman.restaurantmanagement.application.dto.product;

import com.ironman.restaurantmanagement.shared.page.PageableRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductFilterDto extends PageableRequest {
    private String name;
    private String description;
    private Long categoryId;
    private Integer stock;
    private String state;
    private LocalDate createdAtFrom;
    private LocalDate createdAtTo;
}
