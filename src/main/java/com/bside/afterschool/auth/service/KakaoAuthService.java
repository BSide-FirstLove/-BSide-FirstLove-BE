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
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        // 카카오 회원정보 조회 & user모델 저장데이터 셋팅
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String socialId = kakaoMember.getSocialId();

        // 앱토근생성
        JwtToken appToken = jwtTokenProvider.createUserAppToken(socialId);

        // 기존회원 유무 확인
        User user = userRepository.findBySocialId(socialId);

        // TODO return 추가정보 유무 확인필요
        if (ObjectUtils.isEmpty(user)) {
            userRepository.save(kakaoMember);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewMember(Boolean.TRUE)
                    .build();
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewMember(Boolean.FALSE)
                .build();
    }
}
