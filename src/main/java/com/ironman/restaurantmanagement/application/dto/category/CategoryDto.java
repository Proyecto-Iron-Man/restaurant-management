package com.ironman.restaurantmanagement.application.dto.category;

import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.*;

import java.time.LocalDateTime;

// Lombok annotations
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String urlKey;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
