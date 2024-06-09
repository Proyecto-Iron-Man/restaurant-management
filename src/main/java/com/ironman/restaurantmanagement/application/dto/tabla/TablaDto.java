package com.ironman.restaurantmanagement.application.dto.tabla;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TablaDto {
    private Long id;
    private String name;
    private String description;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
