package com.ironman.restaurantmanagement.shared.page;


// Lombok annotations
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class PageableRequest {
    private int page;
    private int size;
    private String sortField;
    private String sortOrder;
}
