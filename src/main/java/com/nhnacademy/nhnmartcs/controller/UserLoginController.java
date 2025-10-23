package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.User;
import com.nhnacademy.nhnmartcs.repository.UserRepository;
import jakarta.servlet.http.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserLoginController {

    private final UserRepository userRepository;

    public UserLoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest req,
                          HttpServletResponse res,
                          Model model) {

        if(userRepository.matches(id, pwd)) { // 일치함
            HttpSession session = req.getSession(true); // 쿠키가 req에 담겨서 오기 때문에 여기서 getSession하는거임
            // ---> session 있으면 가져오고 없으면 생성해라

            session.setAttribute("LOGIN_USER_ID", id); // 로그인 된 유저의 아이디를 세션에 저장함

            // 클라이언트한테 선물할 쿠키
//            Cookie cookie = new Cookie("SESSION", session.getId()); // "SESSION"이름으로 session id를 담음
//            cookie.setPath("/");
//            res.addCookie(cookie);

//            User user = userRepository.getUser(id);
//            model.addAttribute("user", user); // 로그인한 유저 담아서 뷰에 보내줌

            return "redirect:/complainList";
        }
        else {
            log.debug("잘못된 ID나 PW를 입력하였습니다.");
            return "redirect:/login";
        }
    }
}
