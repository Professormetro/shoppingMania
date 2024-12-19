package org.chernov.authservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chernov.authservice.dto.RegisterDto;
import org.chernov.authservice.entity.AppUser;
import org.chernov.authservice.repository.UserRepository;
import org.chernov.authservice.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserRepository userRepository;
    private final AppUserService appUserService;


    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("homePrint", "Welcome to the Home Page");
        return "home";
    }


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute @Valid RegisterDto registerDto,
                               BindingResult result) {

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "confirmPassword", "Passwords and Confirm Password do not match"));
        }

        AppUser appUser = userRepository.findByEmail(registerDto.getEmail());
        if(appUser != null) {
            result.addError(new FieldError("registerDto", "email", "Email is already in use"));
        }

        if(result.hasErrors()) {
            return "register";
        }

        appUserService.encodePasswordAndSaveUser(registerDto);

        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", true);

        return "register";
    }


}
