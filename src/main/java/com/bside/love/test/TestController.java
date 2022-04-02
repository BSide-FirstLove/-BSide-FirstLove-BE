package com.bside.love.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/11")
    public void test() {
        System.out.println("tsd");
    }
}
