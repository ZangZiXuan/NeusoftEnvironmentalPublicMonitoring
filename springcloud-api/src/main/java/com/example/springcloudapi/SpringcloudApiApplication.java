package com.example.springcloudapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@Slf4j
public class SpringcloudApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudApiApplication.class, args);
    }

}
