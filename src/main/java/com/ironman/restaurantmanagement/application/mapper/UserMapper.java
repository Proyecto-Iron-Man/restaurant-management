package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.user.*;
import com.ironman.restaurantmanagement.presistence.entity.UserEntity;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.*;

// MapStruct annotations
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, ProductMapper.class}
)
public interface UserMapper {
    UserDto toDto(UserEntity user);

    @Mapping(target = "fullName", source = ".", qualifiedByName = "fullNameUser")
    UserSmallDto toSmallDto(UserEntity user);

    UserSavedDto toSavedDto(UserEntity user);

    UserSecurityDto toSecurityDto(UserEntity user);


    UserEntity toEntity(UserCreateDto userCreate);

    void updateEntity(@MappingTarget UserEntity user, UserUpdateDto userUpdate);


    @Named("fullNameUser")
    default String fullNameUser(UserEntity user) {
        String fullName = user.getName() + " " + user.getLastName();

        return fullName.trim();
    }

}
