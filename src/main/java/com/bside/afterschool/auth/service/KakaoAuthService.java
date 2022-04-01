package com.bside.afterschool.auth.service;

import com.bside.afterschool.auth.client.ClientKakao;
import com.bside.afterschool.auth.dto.AuthRequest;
import com.bside.afterschool.auth.dto.AuthResponse;
import com.bside.afterschool.auth.security.jwt.JwtToken;
import com.bside.afterschool.auth.security.jwt.JwtTokenProvider;
import com.bside.afterschool.user.domain.User;
import com.bside.afterschool.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    @Autowired
    private ClientKakao clientKakao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    /**
     * 카카오 로그인
     * @param authRequest
     * @return
     */
    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        // 카카오 회원정보 조회 & user모델 저장데이터 셋팅
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String socialId = kakaoMember.getSocialId();

        // 기존회원 유무 확인
        User user = userRepository.findBySocialId(socialId);

        if (ObjectUtils.isEmpty(user)) {
            // 기존유저 아닌경우
            // return 카카오닉네임, 새유저 여부 true
            userRepository.save(kakaoMember);
            return AuthResponse.builder()
                    .isNewMember(Boolean.TRUE)
                    .nickname(kakaoMember.getName())
                    .build();
        } else {
            // else 로그인처리
            // 앱토근생성 및 로그인
            JwtToken appToken = jwtTokenProvider.createUserAppToken(socialId);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewMember(Boolean.FALSE)
                    .build();
        }
    }

    /**
     * 회원가입처리
     * @param authRequest
     * @return
     */
    @Transactional
    public AuthResponse signIn(AuthRequest authRequest) {
        // 카카오 회원정보 조회 & user모델 저장데이터 셋팅
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());

        // TODO 추가정보 셋팅 임시생성( clientKakao와 병합 필요 )
        User.builder()
                .enterYear(StringUtils.isNotEmpty(authRequest.getEnterYear()) ? authRequest.getEnterYear() : "")
                .endYear(StringUtils.isNotEmpty(authRequest.getEndYear()) ? authRequest.getEndYear() : "")
                .schoolName(StringUtils.isNotEmpty(authRequest.getSchoolName()) ? authRequest.getSchoolName() : "")
                .description(StringUtils.isNotEmpty(authRequest.getDescription()) ? authRequest.getDescription() : "")
                .job(StringUtils.isNotEmpty(authRequest.getJob()) ? authRequest.getJob() : "")
                .instagramUrl(StringUtils.isNotEmpty(authRequest.getInstagramUrl()) ? authRequest.getInstagramUrl() : "")
                .build();

        String socialId = kakaoMember.getSocialId();

        // 앱토근생성
        JwtToken appToken = jwtTokenProvider.createUserAppToken(socialId);

        // 유저정보 저장
        // TODO return데이터 확인 및 수정필요
        userRepository.save(kakaoMember);
        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .resultCode(HttpStatus.OK.toString())
                .resultMsg("signIn success")
                .build();

    }
}
