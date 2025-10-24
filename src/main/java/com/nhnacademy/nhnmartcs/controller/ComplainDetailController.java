package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ComplainDetailController {

    private final ComplainRepository complainRepository;
    public ComplainDetailController(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }

    @GetMapping("/cs/customer/{complainId}")
    public String complainDetail(@PathVariable("complainId") long complainId, Model model, HttpSession session) {

        String id = session.getAttribute("LOGIN_USER_ID").toString();

        Complain complain = complainRepository.getComplain(complainId);
        model.addAttribute("complain", complain);
        model.addAttribute("id", id);

        return "complainDetail";
    }
}
