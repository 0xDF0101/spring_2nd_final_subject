package com.nhnacademy.nhnmartcs.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Getter
public class User {

    String id;
    String pwd;
    String name;
    @Setter
    List<Long> complainIdList;  // 일단 아이디만 담아두고 나중에 ComplainRepo 뒤져서 보여주기
    // ---> 객체 생성할때 생성하면 됨
    Role role;

    // ---------->>>> 이건 여기가 아니라 Controller로 가야할 듯 <<<<-----------
    // '문의하기' 기능에 넣으면 아주 딱이겠구만
    public Complain createComplain(RegisterComplainRequest req) {
        // 일단 complainId는 배정받기 전이니까 임시로 -1 부여
        return new Complain(-1, req.getTitle(), req.getContent(),
                req.getCategory(), LocalDateTime.now(), id, req.getFileAttachment(), false);
    }

   /*
   - 아이디
- 비밀번호
- 이름
- `List<Complain>`
- Role Enum
    */

}
