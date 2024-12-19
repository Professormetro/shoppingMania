package org.chernov.admin_shops_service.service;

import lombok.RequiredArgsConstructor;
import org.chernov.admin_shops_service.entity.AppUser;
import org.chernov.admin_shops_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Optional<AppUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
