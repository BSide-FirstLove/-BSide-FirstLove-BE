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
        authRequest.setIsRegist(Boolean.FALSE);
        User kakaoMember = clientKakao.getUserData(authRequest);
        String socialId = kakaoMember.getSocialId();

        // 기존회원 유무 확인
        User user = userRepository.findBySocialId(socialId);

        if (ObjectUtils.isEmpty(user)) {
            // 기존유저 아닌경우
            // return 카카오닉네임, 새유저 여부 true
            return AuthResponse.builder()
                    .isNewMember(Boolean.TRUE)
                    .nickname(kakaoMember.getName())
                    .resultCode("0000")
                    .resultMsg("success")
                    .build();
        } else {
            // else 로그인처리
            // 앱토근생성 및 로그인
            JwtToken appToken = jwtTokenProvider.createUserAppToken(socialId);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewMember(Boolean.FALSE)
                    .resultCode("0000")
                    .resultMsg("success")
                    .build();
        }
    }

    /**
     * 회원가입처리
     * @param authRequest
     * @return
     */
    @Transactional
    public AuthResponse regist(AuthRequest authRequest) {
        // 카카오 회원정보 조회 & user모델 저장데이터 셋팅
        authRequest.setIsRegist(Boolean.TRUE);
        User kakaoMember = clientKakao.getUserData(authRequest);

        String socialId = kakaoMember.getSocialId();

        // 앱토근생성
        JwtToken appToken = jwtTokenProvider.createUserAppToken(socialId);

        // 유저정보 저장
        userRepository.save(kakaoMember);
        // TODO return데이터 확인 및 수정필요
        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .resultCode(HttpStatus.OK.toString())
                .resultMsg("signIn success")
                .build();

    }
}
