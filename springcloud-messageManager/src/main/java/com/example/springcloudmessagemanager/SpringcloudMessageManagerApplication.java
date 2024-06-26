package com.example.springcloudmessagemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringcloudMessageManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudMessageManagerApplication.class, args);
    }

}
