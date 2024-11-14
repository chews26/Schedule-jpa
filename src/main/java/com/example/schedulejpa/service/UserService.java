package com.example.schedulejpa.service;

import com.example.schedulejpa.dto.user.login.LoginResponseDto;
import com.example.schedulejpa.dto.user.signup.SignUpResponseDto;
import com.example.schedulejpa.entity.User;
import com.example.schedulejpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입 기능
    public SignUpResponseDto signUp(String name, String email, String password) {
        User user = new User(name, email, password);
        User saveUser = userRepository.save(user);
        return new SignUpResponseDto(saveUser.getUserId(), saveUser.getName(), saveUser.getEmail());
    }

    // 로그인 기능
    public LoginResponseDto login(String name, String password) {

    }

    // 회원 유무 검증
    public L
}
