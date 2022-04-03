package com.bside.afterschool.user.service;

import com.bside.afterschool.auth.security.jwt.JwtToken;
import com.bside.afterschool.auth.security.jwt.JwtTokenProvider;
import com.bside.afterschool.common.exception.global.error.exception.BusinessException;
import com.bside.afterschool.common.exception.global.error.exception.ErrorCode;
import com.bside.afterschool.user.domain.User;
import com.bside.afterschool.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    /**
     * 회원 ID 조회
     * @param token
     * @return
     */
    public Long getUserId(String token) {
        JwtToken authToken = jwtTokenProvider.convertAuthToken(token);

        Claims claims = authToken.getTokenClaims(); // Token 검증
        if (claims == null) {
            return null;
        }

        try {
            User user =  userRepository.findBySocialId(claims.getSubject());
            return user.getId();

        } catch (NullPointerException e) {
            throw new BusinessException("사용자가 존재하지 않습니다.", ErrorCode.ENTITY_NOT_FOUND);
        }
    }

    /**
     * 회원정보 추가정보 수정
     * @param token
     * @param user
     * @return
     */
    @Transactional
    public User updateAddInfo(String token, User user) {
        JwtToken authToken = jwtTokenProvider.convertAuthToken(token);

        Claims claims = authToken.getTokenClaims(); // Token 검증
        if (claims == null) {
            return null;
        }

        try {
            User userEntity =  userRepository.findBySocialId(claims.getSubject());
            userEntity.setDescription(user.getDescription());
            userEntity.setJob(user.getJob());
            userEntity.setInstagramUrl(user.getInstagramUrl());

            return userEntity;

        } catch (NullPointerException e) {
            throw new BusinessException("사용자가 존재하지 않습니다.", ErrorCode.ENTITY_NOT_FOUND);
        }
    }

}
