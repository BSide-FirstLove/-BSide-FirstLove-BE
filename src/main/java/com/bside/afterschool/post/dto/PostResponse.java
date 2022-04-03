package com.bside.afterschool.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long postId;
    private Long userId;
    private Long placeId;
    private String contents;
    private String year;
    private Integer likeCnt;
    private List<String> imgPathList = new ArrayList<>();   // 이미지 최대 3개
    private List<String> hashtagList = new ArrayList<>();   // 해시태그 최대 3개
}
