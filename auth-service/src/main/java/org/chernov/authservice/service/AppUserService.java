package org.chernov.authservice.service;


import lombok.RequiredArgsConstructor;
import org.chernov.authservice.dto.RegisterDto;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.entity.Role;
import org.chernov.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AppUser encodePasswordAndSaveUser(RegisterDto registerDto) {

        AppUser appUser = AppUser.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .phone(registerDto.getPhone())
                .role(Role.BUYER)
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        return userRepository.save(appUser);
    }


}
