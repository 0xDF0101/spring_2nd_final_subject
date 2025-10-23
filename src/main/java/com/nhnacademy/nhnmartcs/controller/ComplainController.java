package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Category;
import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.domain.Customer;
import com.nhnacademy.nhnmartcs.domain.FileAttachment;
import com.nhnacademy.nhnmartcs.dto.RegisterComplainRequest;
import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import com.nhnacademy.nhnmartcs.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ComplainController {

    private final UserRepository userRepository;
    private final ComplainRepository complainRepository;
    private static final String UPLOAD_DIR = "/Users/eugene/Desktop/project/NHNmartCs/src/main/resources/uploadFile/";

    public ComplainController(UserRepository userRepository, ComplainRepository complainRepository) {
        this.userRepository = userRepository;
        this.complainRepository = complainRepository;
    }

    @GetMapping("/cs/complain")
    public String complain(HttpSession session, Model model) {
        String id = session.getAttribute("LOGIN_USER_ID").toString();
        model.addAttribute("name", userRepository.getUser(id).getName());
        return "complain";
    }

    @PostMapping("/cs/complain")
    public String registerComplain(@ModelAttribute RegisterComplainRequest complainRequest, HttpSession session, Model model) throws IOException {

        String title = complainRequest.getTitle();
        String content = complainRequest.getContent();
        Category category = complainRequest.getCategory(); // 자동으로 변환해주나보네?
        List<FileAttachment> files = new ArrayList<>(); // 담아서 넘겨줄 리스트
        List<MultipartFile> rawFiles = complainRequest.getFiles(); // 가져온 파일

        String id = session.getAttribute("LOGIN_USER_ID").toString();

        // 파일을 실제 저장하고, fileAttachment 형식으로 바꿈
        for(MultipartFile rawFile : rawFiles) {
            rawFile.transferTo(Paths.get(UPLOAD_DIR + rawFile.getOriginalFilename()));
            FileAttachment file = new FileAttachment(UUID.randomUUID().toString(),
                    rawFile.getOriginalFilename(), UPLOAD_DIR + rawFile.getOriginalFilename(),
                    rawFile.getSize());
            files.add(file);
        }
        long complainId = complainRepository.register(title, content, category, id, files);
        Customer customer = (Customer) userRepository.getUser(id);
        customer.getComplainIdList().add(complainId);


        return "redirect:/cs";
    }
}
