package com.bside.afterschool.common.annotation.handlerMethod;

import com.bside.afterschool.auth.security.jwt.JwtTokenProvider;
import com.bside.afterschool.common.annotation.JwtUser;
import com.bside.afterschool.common.annotation.dto.UserResolverDto;
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

        Claims claims = tokenProvider.parseClaims(authorizationHeader);
        System.out.println(claims);
        System.out.println("jwt체크온다.");

        return null;
    }
}
