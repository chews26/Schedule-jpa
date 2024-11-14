package com.example.schedulejpa.dto.user.signup;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotEmpty(message = "이름은 필수 입력 항목입니다.")
    private final String name;

    @NotEmpty(message = "이메일(아이디)은 필수 입력 항목입니다.")
    private final String email;

    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    private final String password;

    public SignUpRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
