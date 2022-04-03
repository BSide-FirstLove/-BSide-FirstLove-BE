package com.bside.afterschool.test;

import com.bside.afterschool.auth.security.jwt.JwtHeaderUtil;
import com.bside.afterschool.auth.security.service.KakaoAuthService;
import com.bside.afterschool.common.exception.global.response.ApiResDto;
import com.bside.afterschool.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API 테스트용
 */
@Slf4j
@RestController
public class ApiTestController {

    @Autowired
    private KakaoAuthService kakaoAuthService;
    @Autowired
    private UserService userService;

    // http://localhost:8000/test/hello
    @GetMapping("/test/hello")
    public List<Map<String, Object>> hello() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("낙곱새", "매운맛");
        map.put("삼겹살", "쌈장");
        map.put("호로로", "달콤");
        map.put("우거지", "감자탕");
        map.put("배포테스트", "배포테스트");
        list.add(map);
        return list;
    }

    /**
     * JWT 토큰검증 TEST용
     * @return
     */
    @GetMapping("/test/checkToken")
    public ResponseEntity<?> checkJwtToken(HttpServletRequest request) {

        String token = JwtHeaderUtil.getAccessToken(request);   // Bearer를 뺀 token값 얻기

        Long memberId = userService.getUserId(token); // token 검증 및 회원id조회

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("memberId", memberId == null ? "" : memberId);
        rtnMap.put("token", token);

        return new ResponseEntity<>(new ApiResDto<>(1, "JWT 토큰 검증 완료", rtnMap), HttpStatus.OK);
    }

}
