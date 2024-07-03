package com.ironman.restaurantmanagement.application.dto.customer;

import com.ironman.restaurantmanagement.application.dto.documenttype.DocumentTypeSmallDto;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String lastName;
    private DocumentTypeSmallDto documentType;
    private String documentNumber;
    private String phoneNumber;
    private String address;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
