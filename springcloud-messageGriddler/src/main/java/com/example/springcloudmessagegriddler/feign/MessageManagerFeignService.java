package com.example.springcloudmessagegriddler.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/2 20:20
 * @Version 1.0
 */
@FeignClient(name = "springcloud-messageManager",url = "localhost:8086")
public interface MessageManagerFeignService {
    @GetMapping("/updateMessageStatus")
    public Map<String, Object> updateMessageStatus(@RequestParam("messageId") String messageId);
}
