package com.bside.afterschool.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TestRequestDto {

    @NotBlank(message = "값이 필수입니다.")
    private String name;
}
