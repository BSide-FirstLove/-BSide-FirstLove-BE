package com.bside.love.user.controller;

import com.bside.love.auth.dto.AuthRequest;
import com.bside.love.auth.dto.AuthResponse;
import com.bside.love.common.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    /**
     * 메인화면 정보 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<AuthResponse> getUserMain(@RequestBody AuthRequest authRequest) {
        return ApiResponse.success(null);
    }
}
