package com.ironman.restaurantmanagement.application.dto.category;


import lombok.*;

// Lombok annotations
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySmallDto {
    private Long id;
    private String name;
}
