package com.example.schedulejpa.dto.user.user;

import lombok.Getter;

@Getter
public class UserResponseDto {

    // 유저 식별자
    private final String userId;

    // e-mail형식의 login ID
    private final String email;

    // 유저 이름
    private final String name;

    public UserResponseDto(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}
