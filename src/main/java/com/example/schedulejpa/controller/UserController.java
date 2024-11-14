package com.example.schedulejpa.controller;

import com.example.schedulejpa.dto.user.login.LoginRequestDto;
import com.example.schedulejpa.dto.user.login.LoginResponseDto;
import com.example.schedulejpa.dto.user.signup.SignUpRequestDto;
import com.example.schedulejpa.dto.user.signup.SignUpResponseDto;
import com.example.schedulejpa.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 기능
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // todo 로그인 기능
    @PostMapping("/login")
    public String login(
            @Valid
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {
        // 로그인 유저 조회
        LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());

        // 로그인 실패 예외처리
        // 실패할 경우 login 재실행 (현 페이지)
        if (responseDto.getEmail() == null) {
            return "login";
        }

        // 로그인 성공 처리
        // Session의 Default Value는 true이다.
        // Session이 request에 존재하면 기존의 Session을 반환하고,
        // Session이 request에 없을 경우에 새로 Session을 생성한다.
        HttpSession session = request.getSession();

        // 회원 정보 조회
        LoginResponseDto loginResponseDto = userService.findById(userId);

        // Schedule 페이지로 리다이렉트
        return "redirect:/schedule";
    }

    // logout 기능
    @PostMapping("/logout")
    public String logout(
            HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("email", null);

        // 쿠키를 0초로 세팅하여 사라지게 만듬
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // Schedule 페이지로 리다이렉트
        return "redirect:/schedule";
    }
}
