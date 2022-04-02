package com.bside.afterschool.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonRespDto<T> {

    private int resultCode;   // 1 성공 , 9999 실패
    private String message;
    private T data;

}
