package com.example.neps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//exclude = {DataSourceAutoConfiguration.class}
@ComponentScan(basePackages = {"com.example.neps.mapper"})
@SpringBootApplication
public class NepsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NepsApplication.class, args);
    }

}
