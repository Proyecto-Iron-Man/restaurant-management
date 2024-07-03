package com.ironman.restaurantmanagement.application.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSmallDto {
    private Long id;
    private String fullName;
    private Long documentTypeId;
}
