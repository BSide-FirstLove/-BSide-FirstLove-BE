package com.bside.afterschool.common.controller;

import com.bside.afterschool.common.util.NcloudUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class ImageController {

    private final NcloudUploader ncloudUploader;

    @GetMapping
    public void get() {
        System.out.println("test");
    }


    @PostMapping
    public String upload(@RequestParam("images")MultipartFile multipartFile) throws IOException {

        System.out.println(multipartFile);
        return "test";
    }
}
