package com.example.schedulejpa.controller.user;

import com.example.schedulejpa.common.Const;
import com.example.schedulejpa.dto.user.login.LoginRequestDto;
import com.example.schedulejpa.dto.user.login.LoginResponseDto;
import com.example.schedulejpa.dto.user.signup.SignUpRequestDto;
import com.example.schedulejpa.dto.user.signup.SignUpResponseDto;
import com.example.schedulejpa.service.UserService;
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
        LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getUserId();

        // 로그인 성공시 로직
        // Session의 Default Value는 true이다.
        // Session이 request에 존재하면 기존의 Session을 반환하고,
        // Session이 request에 없을 경우에 새로 Session을 생성한다.
        HttpSession session = request.getSession();

        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_USER, responseDto);

        // 로그인 성공시 리다이렉트
        return "redirect:/session-home";
    }

    // logout 기능
    @PostMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
// 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 제거
        }

        // Schedule 페이지로 리다이렉트
        return "redirect:/schedule";
    }
}
