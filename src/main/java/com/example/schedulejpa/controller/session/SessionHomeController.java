package com.example.schedulejpa.controller.session;

import com.example.schedulejpa.common.Const;
import com.example.schedulejpa.dto.user.login.LoginRequestDto;
import com.example.schedulejpa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class SessionHomeController {
    
    private final UserService userService;

    @GetMapping({"/","/schedule"})
    public String home(
            @Valid
            @SessionAttribute(name = Const.LOGIN_USER, required = false) LoginRequestDto loginUser,
            Model model
    ) {

        // session에 loginUser가 없으면 Login 페이지로 이동
        if (loginUser == null) {
            return "login";
        }
        // Session이 정상적으로 조회되면 로그인된것으로 간주
        model.addAttribute("loginUser", loginUser);

        return "schedules";
    }
}