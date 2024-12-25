package org.chernov.authservice.service;

import lombok.RequiredArgsConstructor;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByPhone(phone);

        if (appUser == null) {
            throw new UsernameNotFoundException("User with this phone was not found: " + phone);
        }
        return new CustomUserDetails(appUser);
    }

}
