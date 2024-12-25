package org.chernov.authservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDto{

    @NotEmpty(message = "First name cannot be empty")
    private String username;

    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    @Size(min = 6, message = "Minimum password length is 6 characters")
    private String password;

    private String confirmPassword;

}
