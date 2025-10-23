package com.nhnacademy.nhnmartcs.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserLogoutController {

    @PostMapping("/cs/logout")
    public String logout(HttpServletRequest req,
                         HttpServletResponse res,
                         HttpSession session) {

        if(session!=null) {
            session.invalidate(); // // 세션 무효화
        }

        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("SESSION")) {
                    cookie = new Cookie("SESSION", "");
                    cookie.setMaxAge(0); // 쿠키 지속시간 0
                    cookie.setPath("/");
                    res.addCookie(cookie); // 껍데기만 남은 쿠키 보내주기
                }
            }
        }

        log.info("로그아웃 되었습니다.");
        return "redirect:/cs/login";
    }
}
