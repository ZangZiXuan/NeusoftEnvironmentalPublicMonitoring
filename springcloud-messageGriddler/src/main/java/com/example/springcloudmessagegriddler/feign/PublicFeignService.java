package com.example.springcloudmessagegriddler.feign;

import com.example.springcloudapi.dao.entity.Public;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/30 10:24
 * @Version 1.0
 */
@FeignClient(name = "springcloud-Public",url = "localhost:8089")
public interface PublicFeignService {
    @PostMapping("/publicDetail")
    public Public getPublicById(@RequestBody String publicId);
}
