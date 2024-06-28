package com.example.springcloudmessagegriddler.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 16:02
 * @Version 1.0
 */
@FeignClient(name = "springcloud-messagePublic",url = "localhost:8087")
public interface MessagePublicFeignService {
    @PostMapping("/messagePublic/selectMessagePublic")
    public Map<String,Object> selectMessagePublic(@RequestParam("publicId") String publicId);
}
