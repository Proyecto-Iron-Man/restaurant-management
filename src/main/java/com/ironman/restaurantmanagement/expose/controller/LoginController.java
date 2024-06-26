package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.user.AuthDto;
import com.ironman.restaurantmanagement.application.dto.user.UserSecurityDto;
import com.ironman.restaurantmanagement.application.service.UserService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// OpenAPI annotations
@SecurityRequirements(value = {})

// Lombok annotations
@RequiredArgsConstructor

// Stereotype annotation
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @ApiResponse(responseCode = StatusCode.OK,description = "Authenticate user")
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
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<UserSecurityDto> authenticate(@Valid @RequestBody AuthDto auth) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.login(auth));
    }

}
