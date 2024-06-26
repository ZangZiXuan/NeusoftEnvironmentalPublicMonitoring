package com.example.springcloudmessagegriddler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringcloudMessageGriddlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudMessageGriddlerApplication.class, args);
    }

}
