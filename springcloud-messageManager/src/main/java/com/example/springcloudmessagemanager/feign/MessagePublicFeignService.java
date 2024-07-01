package com.example.springcloudmessagemanager.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/1 11:11
 * @Version 1.0
 */
@FeignClient(name = "springcloud-messagePublic",url = "localhost:8087")
public interface MessagePublicFeignService {
    @GetMapping("/messagePublic/ViewMyMessagePublic/{publicId}")
    public Map<String,Object> ViewMyMessagePublic(@PathVariable("publicId") String publicId);
    @PostMapping("/messagePublic/selectMessagePublic")
    public Map<String,Object> selectMessagePublic(@RequestParam("messageId") String messageId);
}
