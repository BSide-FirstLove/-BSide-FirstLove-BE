package com.bside.afterschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BSideAfterSchoolApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:ncloud.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(BSideAfterSchoolApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
