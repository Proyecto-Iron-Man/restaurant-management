package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSavedDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSmallDto;
import com.ironman.restaurantmanagement.application.mapper.ProfileMapper;
import com.ironman.restaurantmanagement.application.service.ProfileService;
import com.ironman.restaurantmanagement.presistence.entity.Profile;

import com.ironman.restaurantmanagement.presistence.repository.ProfileRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

// Spring Stereotype annotation
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public List<ProfileSmallDto> findAll() {

        return profileRepository.findAll()
                .stream()
                .map(profileMapper::toSmallDto)
                .toList();
    }

    @Override
    public ProfileDto findById(Long id) {
        return profileRepository.findById(id)
                .map(profileMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProfileSavedDto create(ProfileBodyDto profilesBody) {
        Profile profile = profileMapper.toEntity(profilesBody);
        profile.setState(State.ENABLED.getValue());
        profile.setCreatedAt(LocalDateTime.now());

        return profileMapper.toSavedDto(profileRepository.save(profile));
    }

    @Override
    public ProfileSavedDto update(Long id, ProfileBodyDto profilesBody) {
        Profile profile = profileRepository.findById(id).get();

        profileMapper.updateEntity(profile, profilesBody);
        profile.setUpdatedAt(LocalDateTime.now());

        return profileMapper.toSavedDto(profileRepository.save(profile));
    }

    @Override
    public ProfileSavedDto disable(Long id) {
        Profile profile = profileRepository.findById(id).get();
        profile.setState(State.DISABLED.getValue());

        return profileMapper.toSavedDto(profileRepository.save(profile));
    }

    @Override
    public List<ProfileSmallDto> findByState(String state) {
        return profileRepository.findByStateOrderByIdDesc(state)
                .stream()
                .map(profileMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<ProfileSmallDto> findByName(String name) {
        return profileRepository.findByName(name)
                .stream()
                .map(profileMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<ProfileSmallDto> findAllByFilters(String name, String state) {
        return profileRepository.findAllByFilters(name, state)
                .stream()
                .map(profileMapper::toSmallDto)
                .toList();
    }

}
