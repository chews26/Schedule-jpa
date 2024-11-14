package com.example.schedulejpa.dto.user.login;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    @Email
    private final String email;

    private final Long userId;

    public LoginResponseDto(String email, Long userId) {
        this.email = email;
        this.userId = userId;
    }
}
