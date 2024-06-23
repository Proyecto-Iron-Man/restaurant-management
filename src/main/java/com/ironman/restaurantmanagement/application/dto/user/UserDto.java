package com.ironman.restaurantmanagement.application.dto.user;

import com.ironman.restaurantmanagement.application.dto.profile.ProfileSmallDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private ProfileSmallDto profile;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
