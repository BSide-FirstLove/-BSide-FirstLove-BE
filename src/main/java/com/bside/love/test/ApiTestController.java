package com.bside.love.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API 테스트용
 */
@RestController
public class ApiTestController {

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

}
