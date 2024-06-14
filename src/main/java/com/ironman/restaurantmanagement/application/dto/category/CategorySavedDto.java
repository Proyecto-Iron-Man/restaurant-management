package com.ironman.restaurantmanagement.application.dto.category;

import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySavedDto {
    private Long id;
    private State state;
}
