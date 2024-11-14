package com.example.schedulejpa.dto.user.login;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    @Email
    private final String email;

    public LoginResponseDto(String email) {
        this.email = email;
    }
}
