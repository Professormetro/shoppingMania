package org.chernov.authservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chernov.authservice.dto.AuthResponse;
import org.chernov.authservice.dto.LoginDto;
import org.chernov.authservice.dto.RegisterDto;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.repository.UserRepository;
import org.chernov.authservice.service.AppUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final AppUserService appUserService;

//    @GetMapping("/home")
//    public String showHomePage(Model model, HttpServletRequest request) {
//
//        String token = request.getHeader("Authorization");
//
//        model.addAttribute("token", token);
//        return "home";
//    }



    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@ModelAttribute @Valid LoginDto loginDto, BindingResult result){

//        if(user == null){
//            result.addError(new FieldError("loginDto", "phone", "Cannot find user with this phone number"));
//        }

        //        if(result.hasErrors()){
//            return "login";
//
//        }
        String token = appUserService.authenticateUserAndCreateToken(loginDto, result);

        if(token == null){
            result.addError(new FieldError("loginDto", "phone", "Token is invalid"));
        }

        String catalogUrl = "http://localhost:8090/api/products";

        try{
            String catalogResponse = restTemplate.getForObject(catalogUrl, String.class);
            return ResponseEntity.ok(new AuthResponse(token, catalogResponse));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(token, "Catalog-service is not available"));
        }

    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterDto registerDto,
                               BindingResult result) {

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "confirmPassword", "Passwords and Confirm Password do not match"));
        }

        AppUser appUser = userRepository.findByPhone(registerDto.getPhone());
        if(appUser != null) {
            result.addError(new FieldError("registerDto", "phone", "This phone number is already in use"));
        }

        if(result.hasErrors()) {
            return "register";
        }

        appUserService.encodePasswordAndSaveUser(registerDto);

        return "redirect:/login";
    }


}
