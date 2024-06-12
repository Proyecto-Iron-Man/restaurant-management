package com.ironman.restaurantmanagement.application.mapper;


import com.ironman.restaurantmanagement.application.dto.profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSavedDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSmallDto;
import com.ironman.restaurantmanagement.presistence.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {

    ProfileDto toDto(Profile profile);

    ProfileSmallDto toSmallDto(Profile profile);

    ProfileSavedDto toSavedDto(Profile profile);


    Profile toEntity(ProfileBodyDto profileBody);

    Profile updateEntity(@MappingTarget Profile profile, ProfileBodyDto profileBody);
}
