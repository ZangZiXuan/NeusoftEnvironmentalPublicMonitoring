package com.example.springcloudmessagepublic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.minio.MinioClient;

@Configuration
public class MinioConfig {


    private String minioEndpoint = "http://192.168.40.129:19000";



    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioEndpoint)
                .build();
    }
}
