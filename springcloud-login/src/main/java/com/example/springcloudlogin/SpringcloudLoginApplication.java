package com.example.springcloudlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringcloudLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLoginApplication.class, args);
    }

}
