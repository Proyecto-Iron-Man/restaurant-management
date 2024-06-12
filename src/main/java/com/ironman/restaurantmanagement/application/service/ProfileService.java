package com.ironman.restaurantmanagement.application.service;


import com.ironman.restaurantmanagement.application.dto.profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSavedDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSmallDto;

import java.util.List;

public interface ProfileService {
    List<ProfileSmallDto> findAll();
    ProfileDto findById(Long id);

    ProfileSavedDto create(ProfileBodyDto profilesBody);
    ProfileSavedDto update(Long id, ProfileBodyDto profilesBody);
   ProfileSavedDto disable(Long id);

    List<ProfileSmallDto> findByState(String state);
    List<ProfileSmallDto> findByName(String name);
    List<ProfileSmallDto> findAllByFilters(String name, String state);
}
