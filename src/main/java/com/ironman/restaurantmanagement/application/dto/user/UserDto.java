package com.ironman.restaurantmanagement.application.dto.user;

import com.ironman.restaurantmanagement.application.dto.profile.ProfileSmallDto;
import lombok.*;

import java.time.LocalDateTime;

// Lombok annotations
@EqualsAndHashCode
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
