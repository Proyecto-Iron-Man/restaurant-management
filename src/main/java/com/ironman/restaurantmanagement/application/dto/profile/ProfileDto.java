package com.ironman.restaurantmanagement.application.dto.profile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    private String name;
    private String description;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
