package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.user.*;
import com.ironman.restaurantmanagement.application.mapper.UserMapper;
import com.ironman.restaurantmanagement.application.service.UserService;
import com.ironman.restaurantmanagement.presistence.entity.UserEntity;
import com.ironman.restaurantmanagement.presistence.repository.ProfileRepository;
import com.ironman.restaurantmanagement.presistence.repository.UserRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.security.JwtHelper;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Stereotype annotation
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;


    @Override
    public List<UserSmallDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toSmallDto)
                .toList();
    }

    @Override
    public UserDto findById(Long id) throws DataNotFoundException {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> userDataNotFoundException(id));
    }

    @Override
    public UserSavedDto create(UserCreateDto userCreate) throws DataNotFoundException {
        profileRepository.findById(userCreate.getProfileId())
                .orElseThrow(() -> ProfileDataNotFoundException(userCreate.getProfileId()));

        UserEntity user = userMapper.toEntity(userCreate);
        user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        user.setState(State.ENABLED.getValue());
        user.setCreatedAt(LocalDateTime.now());

        return userMapper.toSavedDto(userRepository.save(user));
    }

    @Override
    public UserSavedDto update(Long id, UserUpdateDto userUpdate) throws DataNotFoundException {
        profileRepository.findById(userUpdate.getProfileId())
                .orElseThrow(() -> ProfileDataNotFoundException(userUpdate.getProfileId()));

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> userDataNotFoundException(id));

        userMapper.updateEntity(user, userUpdate);
        user.setUpdatedAt(LocalDateTime.now());


        return userMapper.toSavedDto(userRepository.save(user));
    }

    @Override
    public UserSavedDto disable(Long id) throws DataNotFoundException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> userDataNotFoundException(id));

        user.setState(State.DISABLED.getValue());

        return userMapper.toSavedDto(userRepository.save(user));
    }

    @Override
    public UserSecurityDto login(AuthDto auth) throws DataNotFoundException {
        UserEntity user = userRepository.findByEmail(auth.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + auth.getEmail()));

        if(!passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
            throw new DataNotFoundException("Invalid password");
        }

        if(user.getState().equals(State.DISABLED.getValue())) {
            throw new DataNotFoundException("User is disabled");
        }


        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getProfile().getName());
        UserDetails userDetails = new User(user.getEmail(), user.getPassword(), List.of(grantedAuthority));


        UserSecurityDto userSecurity = userMapper.toSecurityDto(user);
        userSecurity.setTokenAccess(jwtHelper.generateToken(userDetails));

        return userSecurity;
    }


    private DataNotFoundException userDataNotFoundException(Long id) {
        return new DataNotFoundException("User not found with id: " + id);
    }

    private DataNotFoundException ProfileDataNotFoundException(Long profileId) {
        return new DataNotFoundException("Profile not found with id: " + profileId);
    }
}
