package com.bside.afterschool.post.controller;

import com.bside.afterschool.auth.security.jwt.JwtHeaderUtil;
import com.bside.afterschool.common.exception.global.response.ApiResDto;
import com.bside.afterschool.post.dto.CreatePostRequest;
import com.bside.afterschool.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostController {

    private PostService postService;

    /**
     * 장소별 게시글 등록
     * @param request
     * @param createPostRequest
     * @return
     */
    @PostMapping("/post/{placeId}/regist")
    public ResponseEntity<?> registPost(HttpServletRequest request, @PathVariable Long placeId, @RequestBody CreatePostRequest createPostRequest) {
        String token = JwtHeaderUtil.getAccessToken(request);
        postService.registerPost(token, placeId, createPostRequest);
        return new ResponseEntity<>(new ApiResDto<>(1, "게시글 등록 성공", null), HttpStatus.OK);
    }
}
