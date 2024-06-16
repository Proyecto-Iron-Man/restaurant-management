package com.ironman.restaurantmanagement.application.dto.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSmallDto {
    private Long id;
    private String name;
    private Long categoryId;
}
