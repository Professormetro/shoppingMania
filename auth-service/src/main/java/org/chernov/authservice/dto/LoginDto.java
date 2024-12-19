package org.chernov.authservice.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String phone;
    private String password;
}
