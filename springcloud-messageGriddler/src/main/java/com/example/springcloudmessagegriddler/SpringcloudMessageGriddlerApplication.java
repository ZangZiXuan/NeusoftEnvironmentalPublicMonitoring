package com.example.springcloudmessagegriddler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class SpringcloudMessageGriddlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudMessageGriddlerApplication.class, args);
    }

}
