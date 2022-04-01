package com.bside.afterschool.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String accessToken;

    // TODO 회원가입 추가입력항목
    private String schoolName;  // 학교명
    private String enterYear;   // 입학연도
    private String endYear;     // 졸업연도
    private String instagramUrl;
    private String job;         // 직업
    private String description; // 하고싶은 말


    private Boolean isRegist;
}
