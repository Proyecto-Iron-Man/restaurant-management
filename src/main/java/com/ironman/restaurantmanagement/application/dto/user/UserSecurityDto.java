package com.ironman.restaurantmanagement.application.dto.user;

import lombok.*;

// Lombok annotations
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDto extends UserDto {
    private String tokenAccess;
}
