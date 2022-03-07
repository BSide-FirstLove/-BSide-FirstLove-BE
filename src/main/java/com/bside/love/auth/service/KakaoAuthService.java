package com.bside.love.auth.service;

import com.bside.love.auth.client.ClientKakao;
import com.bside.love.auth.dto.AuthRequest;
import com.bside.love.auth.dto.AuthResponse;
import com.bside.love.auth.security.jwt.JwtToken;
import com.bside.love.auth.security.jwt.JwtTokenProvider;
import com.bside.love.user.domain.User;
import com.bside.love.user.repository.UserRepository;
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
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String socialId = kakaoMember.getSocialId();

        JwtToken appToken = jwtTokenProvider.createUserAppToken(socialId);

        User user = userRepository.findBySocialId(socialId);
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
