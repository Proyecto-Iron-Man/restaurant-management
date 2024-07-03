package com.ironman.restaurantmanagement.application.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSavedDto {
    private Long id;
    private String state;
}
