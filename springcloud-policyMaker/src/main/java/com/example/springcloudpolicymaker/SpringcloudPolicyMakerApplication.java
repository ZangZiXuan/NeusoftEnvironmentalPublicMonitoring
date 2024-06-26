package com.example.springcloudpolicymaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class SpringcloudPolicyMakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudPolicyMakerApplication.class, args);
    }

}
