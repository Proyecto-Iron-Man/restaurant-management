package com.ironman.restaurantmanagement.expose.controller;


import com.ironman.restaurantmanagement.application.dto.profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSavedDto;
import com.ironman.restaurantmanagement.application.dto.profile.ProfileSmallDto;
import com.ironman.restaurantmanagement.application.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileSmallDto> findAll() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public ProfileDto findById(@PathVariable("id") Long id) {
        return profileService.findById(id);
    }

    @GetMapping("/state/{state}")
    public List<ProfileSmallDto> findByState(@PathVariable("state") String state){
        return profileService.findByState(state);
    }

    @GetMapping("/name/{name}")
    public List<ProfileSmallDto> findByName(@PathVariable("name") String name) {
        return profileService.findByName(name);
    }

    @GetMapping("/filters")
    public List<ProfileSmallDto> findAllByFilters(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "state", required = false) String state) {
        return profileService.findAllByFilters(name, state);
    }


    @PostMapping
    public ProfileSavedDto create(@RequestBody ProfileBodyDto profileBody) {
        return profileService.create(profileBody);
    }

    @PutMapping("/{id}")
    public ProfileSavedDto update(@PathVariable("id") Long id, @RequestBody ProfileBodyDto profileBody) {
        return profileService.update(id, profileBody);
    }

    @DeleteMapping("/{id}")
    public ProfileSavedDto disable(@PathVariable("id") Long id) {
        return profileService.disable(id);
    }
}
