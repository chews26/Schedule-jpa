package com.example.schedulejpa.dto.user.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    @NotNull
    @Email
    private final String email;

    public LoginResponseDto(String email) {
        this.email = email;
    }
}
