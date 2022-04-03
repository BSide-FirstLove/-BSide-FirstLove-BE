package com.bside.afterschool.post.dto;

import com.bside.afterschool.place.enumerate.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {

    private PlaceType placeType;    // 장소타입 ( 학교, 학원, 추억의장소 )

    private String contents; // 추억내용
    private String year;        // 추억시기
    private List<String> hashtagList = new ArrayList<>();   // 해시태그 최대 3개
    private List<String> imgPathList = new ArrayList<>();   // 이미지 최대 3개

}
