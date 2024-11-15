package com.example.schedulejpa.controller.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("session")
    public String session(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "세션이 없습니다. 로그인해주세요.";
        }

        // session 정보 조회
        log.info("session.getId()={}", session.getId());
        log.info("session.getMaxInactiveInterval()={}", session.getMaxInactiveInterval());
        log.info("session.getCreationTime()={}", session.getCreationTime());
        log.info("session.getLastAccessedTime()={}", session.getLastAccessedTime());
        log.info("session.isNew()={}", session.isNew());

        return "세션이 정상적으로 조회되었습니다.";
    }
}