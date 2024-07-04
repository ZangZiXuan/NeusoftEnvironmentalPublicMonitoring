package com.example.neusoftgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NeusoftGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeusoftGatewayApplication.class, args);
	}

}
