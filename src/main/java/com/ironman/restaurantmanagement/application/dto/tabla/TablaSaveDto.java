package com.ironman.restaurantmanagement.application.dto.tabla;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TablaSaveDto {
    private Long id;
    private String state;
}
