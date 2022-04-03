package com.bside.afterschool.common.exception.global.response;

import lombok.*;

@NoArgsConstructor
@Data
public class ApiResDto<T> {

    private int resultCode;   // 1 성공 , 9999 실패
    private String message;
    private T data;

    @Builder
    public ApiResDto(int resultCode, String message, T data){
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }
}
