package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.domain.Customer;
import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import com.nhnacademy.nhnmartcs.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class ComplainListController {

    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    public ComplainListController(ComplainRepository complainRepository, UserRepository userRepository) {
        this.complainRepository = complainRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/cs/customer")
    public String complainList(HttpSession session, Model model) {

        String id = session.getAttribute("LOGIN_USER_ID").toString();

        Customer customer = (Customer) userRepository.getUser(id);
        // id list를 넘겨주면 complain 리스트를 반환
        List<Complain> complains = complainRepository.getCustomerComplain(customer.getComplainIdList());

        Collections.reverse(complains);

        model.addAttribute("complains", complains);
        model.addAttribute("customer", customer);

        return "complainList";
    }
}
