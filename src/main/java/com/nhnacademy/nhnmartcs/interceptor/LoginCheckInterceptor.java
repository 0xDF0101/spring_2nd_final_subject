package com.nhnacademy.nhnmartcs.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session!=null && session.getAttribute("LOGIN_USER_ID") != null) {
            log.debug("세션 ID {}로 로그인 됨", session.getId());
            return true;
        }

//        log.warn("로그인되지 않은 사용자의 접근 시도");
        response.sendRedirect("/login");
        return false;
    }
}

