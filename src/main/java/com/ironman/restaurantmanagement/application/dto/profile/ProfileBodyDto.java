package com.ironman.restaurantmanagement.application.dto.profile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileBodyDto {
    private String name;
    private String description;
}
