package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Cs;
import com.nhnacademy.nhnmartcs.domain.Customer;
import com.nhnacademy.nhnmartcs.domain.User;
import com.nhnacademy.nhnmartcs.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginJunctionController {

    private final UserRepository userRepository;

    public LoginJunctionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/cs/junction")
    public String complainList(HttpServletRequest req, HttpServletResponse res, Model model) {

        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("LOGIN_USER_ID").toString();

        User user = userRepository.getUser(userId);
        if(user instanceof Customer customer) {
            return "redirect:/cs/customer";
        }
        else if(user instanceof Cs cs) {
            return "redirect:/cs/admin";
        }
        return "/";
    }
}
