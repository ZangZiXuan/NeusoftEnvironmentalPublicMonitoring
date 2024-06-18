package com.example.springcloudmessagepublic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient(name = "springcloud-api",url = "localhost:8081")
public class SpringcloudMessagePublicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudMessagePublicApplication.class, args);
	}

}
