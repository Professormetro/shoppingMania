package org.chernov.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String catalogResponse;
}