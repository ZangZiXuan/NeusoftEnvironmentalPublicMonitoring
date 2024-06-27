package com.example.springcloudmessagepublic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/27 9:44
 * @Version 1.0
 */
//name = "springcloud-api-province",
@FeignClient(name = "springcloud-province",url = "localhost:8081")
public interface ProvinceFeignService {
    @PostMapping("/selectProvince")
    Map<String,Object> selectProvince(@RequestParam("provinceId") String provinceId);
}
