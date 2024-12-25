package org.chernov.authservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.chernov.authservice.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "cisco";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // Метод для генерации токена
    public String generateToken(String phone, Set<Role> roles) {
        String rolesAsString = roles.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(phone)
                .claim("roles", rolesAsString)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractPhone(String token) {
        return extractAllClaims(token).getSubject();
    }


    public Set<Role> extractRoles(String token) {
        String roles = extractAllClaims(token).get("roles", String.class);
        return roles != null ?
                Arrays.stream(roles.split(","))
                        .map(Role::valueOf) // Преобразуем строку в перечисление Role
                        .collect(Collectors.toSet()) :
                Collections.emptySet();
    }


    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}