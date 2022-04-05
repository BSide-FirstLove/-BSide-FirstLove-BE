package com.bside.afterschool.common.util;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;

//@RequiredArgsConstructor
//@Component
public class NcloudUploader {
//
//    private final AmazonS3 amazonS3;
//
//    public String test() {
//        String bucketName = "sample-bucket";
//
//        String folderName = "sample-folder/";
//
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentLength(0L);
//        objectMetadata.setContentType("application/x-directory");
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);
//
//        try {
//            amazonS3.putObject(putObjectRequest);
//
//            System.out.format("Folder %s has been created.\n", folderName);
//        } catch (AmazonS3Exception e) {
//            e.printStackTrace();
//        } catch(SdkClientException e) {
//            e.printStackTrace();
//        }
//
//// upload local file
//        String objectName = "sample-object";
//        String filePath = "/tmp/sample.txt";
//
//        try {
//            amazonS3.putObject(bucketName, objectName, new File(filePath));
//            System.out.format("Object %s has been created.\n", objectName);
//        } catch (AmazonS3Exception e) {
//            e.printStackTrace();
//        } catch(SdkClientException e) {
//            e.printStackTrace();
//        }
//
//        return "test";
//    }
}
