package com.example.springcloudpublic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
public class SpringcloudPublicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudPublicApplication.class, args);
    }

}
