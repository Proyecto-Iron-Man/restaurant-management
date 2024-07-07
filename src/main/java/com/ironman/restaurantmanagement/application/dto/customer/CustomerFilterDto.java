package com.ironman.restaurantmanagement.application.dto.customer;

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
public class CustomerFilterDto extends PageableRequest {
    private String name;
    private String lastName;
    private Long documentTypeId;
    private String documentNumber;
    private String state;
    private LocalDate createdAtFrom;
    private LocalDate createdAtTo;
}
