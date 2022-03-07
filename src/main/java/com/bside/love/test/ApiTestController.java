package com.bside.love.test;

import com.bside.love.auth.dto.AuthRequest;
import com.bside.love.auth.dto.AuthResponse;
import com.bside.love.auth.service.KakaoAuthService;
import com.bside.love.common.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    // http://localhost:8000/test/hello
    @GetMapping("/test/hello")
    public List<Map<String, Object>> hello() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("낙곱새", "매운맛");
        map.put("삼겹살", "쌈장");
        map.put("호로로", "달콤");
        map.put("우거지", "감자탕");
        list.add(map);
        return list;
    }

    // http://localhost:8000/test/auth/kakao
    @PostMapping("/test/auth/kakao")
    public ResponseEntity<AuthResponse> kakaoAuthRequestTest(@RequestBody AuthRequest authRequest) {
        System.out.println("###########################################");
        System.out.println("authRequest >>>>>>>>>>>>>>> " + authRequest);
        System.out.println("###########################################");
        return ApiResponse.success(kakaoAuthService.login(authRequest));
    }

}
