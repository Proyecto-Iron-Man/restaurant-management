package com.ironman.restaurantmanagement.application.dto.documenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeSavedDto {
    private Long id;
    private String state;
}
