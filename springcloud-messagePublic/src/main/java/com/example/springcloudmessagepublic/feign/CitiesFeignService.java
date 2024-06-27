package com.example.springcloudmessagepublic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/27 9:50
 * @Version 1.0
 */
@FeignClient(name = "springcloud-public",url = "localhost:8081")
public interface CitiesFeignService {
    @RequestMapping("/selectCity")
    Map<String,Object> selectCity(@RequestParam("cityId") String cityId);
}
