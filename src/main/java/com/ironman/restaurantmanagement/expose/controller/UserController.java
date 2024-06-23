package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.user.*;
import com.ironman.restaurantmanagement.application.service.UserService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Lombok annotation
@RequiredArgsConstructor

// Stereotype annotation
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @ApiResponse(responseCode = StatusCode.OK, description = "List of all users")
    @GetMapping
    public ResponseEntity<List<UserSmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "User by id")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "User not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findById(id));
    }


    @SecurityRequirements(value = {})
    @ApiResponse(responseCode = StatusCode.CREATED, description = "User created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Profile not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PostMapping
    public ResponseEntity<UserSavedDto> create(@Valid @RequestBody UserCreateDto userCreate) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(userCreate));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "User updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "User not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserSavedDto> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserUpdateDto userUpdate) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.update(id, userUpdate));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "User disabled")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "User not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<UserSavedDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.disable(id));
    }
}
