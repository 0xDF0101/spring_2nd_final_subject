package com.nhnacademy.nhnmartcs.domain;

import lombok.Getter;
import lombok.Value;

// user가 Complain을 등록할때 사용하는 DTO
@Value // 불변의 값
@Getter
public class RegisterComplainRequest {

    String title;
    String content;
    Category category;
    FileAttachment fileAttachment; // 얘가 좀 불안한데

}
