package com.ironman.restaurantmanagement.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSmallDto {
    private Long id;
    private String fullName;
}
