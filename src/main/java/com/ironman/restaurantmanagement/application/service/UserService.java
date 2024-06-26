package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.user.*;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;

import java.util.List;

public interface UserService {

    List<UserSmallDto> findAll();

    UserDto findById(Long id) throws DataNotFoundException;

    UserSavedDto create(UserCreateDto userCreate) throws DataNotFoundException;

    UserSavedDto update(Long id, UserUpdateDto userUpdate) throws DataNotFoundException;

    UserSavedDto disable(Long id) throws DataNotFoundException;

    UserSecurityDto login(AuthDto auth) throws DataNotFoundException;
}
