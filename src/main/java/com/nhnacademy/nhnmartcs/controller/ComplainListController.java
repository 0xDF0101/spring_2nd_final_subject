package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComplainListController {

    private final ComplainRepository complainRepository;

    public ComplainListController(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }


    @GetMapping("/cs/")
    public String complainList() {


        return "complainList";
    }
}
