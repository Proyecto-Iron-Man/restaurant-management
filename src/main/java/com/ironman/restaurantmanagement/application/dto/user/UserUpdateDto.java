package com.ironman.restaurantmanagement.application.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 2 and 200 characters")
    private String name;

    @Size(max = 200, message = "Last name must be less than 200 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Profile id is required")
    @Positive(message = "Profile id must be positive")
    private Long profileId;
}
