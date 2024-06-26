package com.ironman.restaurantmanagement.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Stereotype annotation
@Component
public class JwtHelper {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-minutes}")
    private Integer expirationMinutes;

    public String generateToken(UserDetails user) {
        // Variables
        Map<String, Object> claims = new HashMap<>();

        // Process
        claims.put("username", user.getUsername());
        claims.put("authorities", user.getAuthorities());

        Date creationDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creationDate);
        calendar.add(Calendar.MINUTE, expirationMinutes);

        SecretKey key = getSecretKey();

        // Result
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(creationDate)
                .expiration(calendar.getTime())
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        SecretKey key = getSecretKey();

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
