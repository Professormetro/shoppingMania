package org.chernov.authservice.service;


import lombok.RequiredArgsConstructor;
import org.chernov.authservice.config.JwtUtil;
import org.chernov.authservice.dto.LoginDto;
import org.chernov.authservice.dto.RegisterDto;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.entity.Role;
import org.chernov.authservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


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

    public void authenticateUserAndCreateToken(LoginDto loginDto) {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getPhone(), loginDto.getPassword()));

            AppUser user = userRepository.findByPhone(loginDto.getPhone());
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        }

    }


}
