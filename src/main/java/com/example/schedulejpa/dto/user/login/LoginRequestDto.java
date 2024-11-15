package com.example.schedulejpa.dto.user.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank
    @Email(message = "이메일(아이디)은 필수 입력 항목입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
