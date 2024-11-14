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

    // todo 로그인 기능
    // DTO에서 유효성 검증으로 수정
    public LoginResponseDto login(String email, String password) {

        // 아이디가 존재하는지 확인
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 잘못되었습니다."));

        // 비밀번호가 일치하는지 확인
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("이메일 또는 비밀번호가 잘못되었습니다.");
        }

        return new LoginResponseDto(user.getEmail(), user.getUserId());
    }
}
