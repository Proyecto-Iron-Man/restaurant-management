package com.ironman.restaurantmanagement.application.dto.documenttype;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeDto {
    private Long id;
    private String name;
    private String description;
    private String sunatCode;
    private int size;
    private int isSizeExact;
    private int isNumeric;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
