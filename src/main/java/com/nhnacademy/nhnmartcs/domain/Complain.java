package com.nhnacademy.nhnmartcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Complain {

    private long ComplainId;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdAt;
    private String customerId;
    private List<FileAttachment> fileAttachments;
    private Boolean isReply;
    private String reply;

    /*
    - 아이디
     제목
- 본문
- 분류 (Enum) : 불만 접수, 제안, 환불/교환, 칭찬, 기타 문의
- 작성 일시
- 작성자
- 첨부파일
     */
}
