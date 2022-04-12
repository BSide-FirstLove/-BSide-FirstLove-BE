package com.bside.afterschool.place.dto;

import com.bside.afterschool.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {

    private String placeId;     // 구글 placd id
    private User user;

}
