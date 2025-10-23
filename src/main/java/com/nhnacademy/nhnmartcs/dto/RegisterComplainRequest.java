package com.nhnacademy.nhnmartcs.dto;

import com.nhnacademy.nhnmartcs.domain.Category;
import com.nhnacademy.nhnmartcs.domain.FileAttachment;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// user가 Complain을 등록할때 사용하는 DTO
@Value // 불변의 값
public class RegisterComplainRequest {

    String title;
    String content;
    Category category;
    List<MultipartFile> files; // 얘가 좀 불안한데
}
