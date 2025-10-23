package com.nhnacademy.nhnmartcs.domain;


public interface User {
    String getId(); // 모든 User는 ID를 가져야 함
    String getPwd(); // 모든 User는 비밀번호를 가져야 함 (로그인 검증 목적)
    String getName(); // 사용자 이름 등 공통 필드 추가 가능
}
