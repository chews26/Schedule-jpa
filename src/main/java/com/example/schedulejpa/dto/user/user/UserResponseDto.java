package com.example.schedulejpa.dto.user.user;

import lombok.Getter;

@Getter
public class UserResponseDto {

    // 유저 식별자
    // e-mail형식의 login ID
    private final String email;

    // 유저 이름
    private final String name;

    public UserResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}