package com.ironman.restaurantmanagement.application.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBodyDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;

    @Size(max = 200, message = "Last name must be less than 200 characters")
    private String lastName;

    @NotNull(message = "DocumentTypeId id is required")
    @Positive(message = "DocumentTypeId id must be positive")
    private Long documentTypeId;

    @NotBlank(message = "Document number is required")
    @Size(min = 8, max = 20, message = "Document number must be between 3 and 20 characters")
    private String documentNumber;

    @Size(max = 25, message = "Phone Number must be less than 25 characters")
    private String phoneNumber;

    @Size(max = 2000, message = "Address must be less than 2000 characters")
    private String address;
}
