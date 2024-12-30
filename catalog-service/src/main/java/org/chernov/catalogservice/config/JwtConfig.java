package org.chernov.catalogservice.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.chernov.catalogservice.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtConfig {

    private final String secretKey;

    public JwtConfig() {
        Dotenv dotenv = Dotenv.load();
        this.secretKey = dotenv.get("SECRET_KEY");
    }

    public Set<Role> extractRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String roles = claims.get("roles", String.class);
        return roles != null ?
                Arrays.stream(roles.split(","))
                        .map(Role::valueOf)
                        .collect(Collectors.toSet()) :
                Collections.emptySet();
    }
}
