package com.example.springcloudmessagepublic.feign;

import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudapi.dao.entity.Public;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/25 13:45
 * @Version 1.0
 */
//name = "springcloud-public-service",
@FeignClient(name = "springcloud-public",url = "localhost:8089")
public interface PublicFeignService {
    @GetMapping ("/public/publicDetail")
    public Public getPublicById(@RequestBody String publicId) ;
}
