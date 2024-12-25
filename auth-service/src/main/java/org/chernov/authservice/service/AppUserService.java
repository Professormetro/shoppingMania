package org.chernov.authservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chernov.authservice.config.JwtUtil;
import org.chernov.authservice.dto.LoginDto;
import org.chernov.authservice.dto.RegisterDto;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.entity.Role;
import org.chernov.authservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;



    public AppUser encodePasswordAndSaveUser(RegisterDto registerDto) {

        AppUser appUser = AppUser.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .phone(registerDto.getPhone())
                .roles(Set.of(Role.BUYER))
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        return userRepository.save(appUser);
    }

    public String authenticateUserAndCreateToken(LoginDto loginDto, BindingResult result) {
        try {

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getPhone(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken); // Здесь происходит аутентификация

            CustomUserDetails userDetails  = (CustomUserDetails) authentication.getPrincipal();


            String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(Role::valueOf)
                    .collect(Collectors.toSet()));


            return token;

        } catch (AuthenticationException e) {
            result.addError(new FieldError("loginDto", "password", "Invalid credentials"));
            return null;
        }

    }


}
