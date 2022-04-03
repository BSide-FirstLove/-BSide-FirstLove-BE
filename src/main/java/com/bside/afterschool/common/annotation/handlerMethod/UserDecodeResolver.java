package com.bside.afterschool.common.annotation.handlerMethod;

import com.bside.afterschool.auth.security.jwt.JwtTokenProvider;
import com.bside.afterschool.common.annotation.JwtUser;
import com.bside.afterschool.common.annotation.dto.UserResolverDto;
import com.bside.afterschool.common.exception.global.error.exception.ErrorCode;
import com.bside.afterschool.common.exception.global.error.exception.TokenException;
import com.bside.afterschool.user.domain.User;
import com.bside.afterschool.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDecodeResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isTokenUser = parameter
                .getParameterAnnotation(JwtUser.class) != null;

        boolean isParameter = parameter.getParameterType().equals(UserResolverDto.class);

        return isTokenUser && isParameter;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory
    ) throws Exception {
        String authorizationHeader = webRequest.getHeader("appToken");

        if (authorizationHeader == null) {
            throw new TokenException("Access Token이 존재하지 않습니다." , ErrorCode.ACCESSTOKEN_NOT_HAVE);
        }

        Claims claims = tokenProvider.parseClaims(authorizationHeader);
        System.out.println(claims.get("sub"));

        Long socialId = Long.getLong((String) claims.get("sub"));

        User memberBySocialId = userRepository.findMemberBySocialId(socialId);
        System.out.println("jwt체크온다.");

        return UserResolverDto.from(memberBySocialId);
    }
}
