package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import com.nhnacademy.nhnmartcs.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ComplainReplyController {

    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    public ComplainReplyController(ComplainRepository complainRepository, UserRepository userRepository) {
        this.complainRepository = complainRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/cs/admin/{complainId}")
    public String complainGetReply(@PathVariable() long complainId, Model model, HttpSession session) {

        Complain complain = complainRepository.getComplain(complainId);
        model.addAttribute("complain", complain);
        model.addAttribute("customer", userRepository.getUser(complain.getCustomerId()).getName());

        return "complainReply";
    }

    @PostMapping("/cs/admin/{complainId}")
    public String complainReply(@PathVariable long complainId,
                                @RequestParam("content") String content,
                                HttpSession session) {
        String id = session.getAttribute("LOGIN_USER_ID").toString();

        Complain complain = complainRepository.getComplain(complainId);
        complain.setReply(content);
        complain.setIsReply(true);
        complain.setReplier(userRepository.getUser(id).getName());
        complain.setReplyAt(LocalDateTime.now());

        return "redirect:/cs/admin";
    }
}
