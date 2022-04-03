package com.bside.afterschool.auth.client;

import com.bside.afterschool.auth.dto.AuthRequest;
import com.bside.afterschool.auth.dto.KakaoUserResponse;
import com.bside.afterschool.auth.enumerate.RoleType;
import com.bside.afterschool.auth.exception.TokenValidFailedException;
import com.bside.afterschool.user.domain.User;
import com.bside.afterschool.user.enumerate.UserProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ClientKakao
 */
@Component
@RequiredArgsConstructor
public class ClientKakao implements ClientProxy {

    private final WebClient webClient;

    /**
     * 카카오 회원정보 조회 및 user모델 저장데이터 셋팅
     * TODO 회원가입 시 받는 정보 추가필요
     * @param authRequest
     * @return
     */
    @Override
    public User getUserData(AuthRequest authRequest) {
        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(authRequest.getAccessToken()))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponse.class)
                .block();

        if(Boolean.TRUE.equals(authRequest.getIsRegist())){
            // 가입, 회원정보저장
            return User.builder()
                    .socialId(String.valueOf(kakaoUserResponse.getId()))
                    .email(kakaoUserResponse.getKakaoAccount().getEmail())
                    .gender(kakaoUserResponse.getKakaoAccount().getGender())
                    .userProvider(UserProvider.KAKAO)
                    .roleType(RoleType.USER)
                    .profileImagePath(kakaoUserResponse.getProperties().getProfileImage() != null ? kakaoUserResponse.getProperties().getProfileImage() : "")
                    .name(authRequest.getNickname())
                    .enterYear(authRequest.getEnterYear())
                    .endYear(authRequest.getEndYear())
                    .schoolName(authRequest.getSchoolName())
                    .description(authRequest.getDescription())
                    .job(authRequest.getJob())
                    .instagramUrl(authRequest.getInstagramUrl())
                    .build();
        } else {
            // 로그인 or 최초접속
            return User.builder()
                    .socialId(String.valueOf(kakaoUserResponse.getId()))
                    .name(kakaoUserResponse.getProperties().getNickname())
                    .build();

        }
    }
}
