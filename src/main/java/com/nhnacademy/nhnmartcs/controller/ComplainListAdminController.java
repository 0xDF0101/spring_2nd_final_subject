package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.domain.Cs;
import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import com.nhnacademy.nhnmartcs.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Controller
public class ComplainListAdminController {

    private final UserRepository userRepository;
    private final ComplainRepository complainRepository;

    public ComplainListAdminController(UserRepository userRepository, ComplainRepository complainRepository) {
        this.userRepository = userRepository;
        this.complainRepository = complainRepository;
    }

    @GetMapping("/cs/admin")
    public String admin(HttpSession session, Model model) {

        Cs cs = (Cs)userRepository.getUser(session.getAttribute("LOGIN_USER_ID").toString());
        List<Complain> complains = new ArrayList<>();
        Map<Long, Complain> complainMap = complainRepository.getAllComplain();

        for(Long complainId : complainMap.keySet()) {
            if(!complainMap.get(complainId).getIsReply()) { // 답변하지 않은 거 선별
                complains.add(complainMap.get(complainId));
            }
        }
        model.addAttribute("complains", complains);
        model.addAttribute("cs", cs);

        return "complainListAdmin";
    }


}
