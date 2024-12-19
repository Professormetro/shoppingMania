package org.chernov.authservice.service;

import lombok.RequiredArgsConstructor;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + email);
        }
        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getRole())
                .build();
    }
}
