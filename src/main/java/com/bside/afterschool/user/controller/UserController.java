package com.bside.afterschool.user.controller;

import com.bside.afterschool.auth.security.jwt.JwtHeaderUtil;
import com.bside.afterschool.common.annotation.JwtUser;
import com.bside.afterschool.common.annotation.dto.UserResolverDto;
import com.bside.afterschool.common.exception.global.response.ApiResDto;
import com.bside.afterschool.user.dto.UserInfoRequest;
import com.bside.afterschool.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 메인화면 정보 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getUserMain(HttpServletRequest request) {

        // token검증 임시로 넣어뒀습니다.
        String token = JwtHeaderUtil.getAccessToken(request);

        Long memberId = userService.getUserId(token);

        return new ResponseEntity<>(new ApiResDto<>(1, "메인화면 정보 조회 성공", memberId), HttpStatus.OK);
    }

    /**
     * 회원 추가정보 수정(입력)
     * @param request
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateAddInfo(HttpServletRequest request, @RequestBody UserInfoRequest userInfoRequest) {
        String token = JwtHeaderUtil.getAccessToken(request);
        return new ResponseEntity<>(new ApiResDto<>(1, "성공 /user/update success", userService.updateAddInfo(token, userInfoRequest.toEntity())), HttpStatus.OK);
    }

    @GetMapping("/test")
    public void test(@JwtUser UserResolverDto userResolverDto) {
        System.out.println(userResolverDto);
    }
}
