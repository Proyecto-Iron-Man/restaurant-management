package com.ironman.restaurantmanagement.application.dto.category;

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
public class CategoryFilterDto extends PageableRequest {
    private String name;
    private String description;
    private String state;
    private LocalDate createdAtFrom;
    private LocalDate createdAtTo;
}
