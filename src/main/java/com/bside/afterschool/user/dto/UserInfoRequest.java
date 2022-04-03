package com.bside.afterschool.user.dto;

import com.bside.afterschool.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {

    private String schoolName;  // 학교명
    private String enterYear;   // 입학연도
    private String endYear;     // 졸업연도
    private String instagramUrl;
    private String job;         // 직업
    private String description; // 하고싶은 말

    public User toEntity(){
        return User.builder()
                .description(description)
                .instagramUrl(instagramUrl)
                .job(job)
                .build();
    }

}
