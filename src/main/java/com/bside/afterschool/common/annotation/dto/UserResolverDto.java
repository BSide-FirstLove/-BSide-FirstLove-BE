package com.bside.afterschool.common.annotation.dto;

import com.bside.afterschool.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResolverDto {

    private Long id;
    private String nickname;

    // https://velog.io/@ljinsk3/%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C%EB%8A%94-%EC%99%9C-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C
    public static UserResolverDto from(User user) {
        return new UserResolverDto(user.getId(), user.getName());
    }
}
