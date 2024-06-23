package com.ironman.restaurantmanagement.shared.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

// Lombok annotation
@RequiredArgsConstructor

// Stereotype annotation
@Component
public class AuthenticationManagerImpl implements AuthenticationManager {
    private final JwtHelper jwtHelper;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = jwtHelper.getUsernameFromToken(token);

        if(username == null && !jwtHelper.validateToken(token)){
            throw new AuthenticationServiceException("Invalid or expired token");
        }

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                authentication.getAuthorities()
        );
    }
}
