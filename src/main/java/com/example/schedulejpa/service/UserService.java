package com.example.schedulejpa.service;

import com.example.schedulejpa.config.PasswordEncoder;
import com.example.schedulejpa.dto.user.login.LoginResponseDto;
import com.example.schedulejpa.dto.user.signup.SignUpResponseDto;
import com.example.schedulejpa.entity.User;
import com.example.schedulejpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    // 회원가입 기능
    public SignUpResponseDto signUp(String name, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword);
        User saveUser = userRepository.save(user);
        return new SignUpResponseDto(saveUser.getUserId(), saveUser.getName(), saveUser.getEmail());
    }

    // 로그인 기능
    public LoginResponseDto login(String email, String password) {

        // 아이디가 존재하는지 확인
        User user = userRepository.findByEmailOrElseThrow(email);

        // 비밀번호가 일치하는지 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일 또는 비밀번호가 잘못되었습니다.");
        }

        return new LoginResponseDto(user.getEmail(), user.getUserId());
    }
}
