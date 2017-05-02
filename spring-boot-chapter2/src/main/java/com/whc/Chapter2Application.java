package com.whc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Chapter2Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }
}
