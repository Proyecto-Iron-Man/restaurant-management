package com.ironman.restaurantmanagement.application.dto.documenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeBodyDto {
    private String name;
    private String description;
    private String sunatCode;
    private int size;
    private int isSizeExact;
    private int isNumeric;
}
