package com.example.springcloudmessagemanager.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/1 11:50
 * @Version 1.0
 */
@FeignClient(name = "springcloud-Base",url = "localhost:8081")
public interface CitiesFeignService {
    @GetMapping("/city/selectCityName")
    public String selectCityName(@RequestParam("cityId") String cityId);
    @GetMapping("/province/selectprovinceName")
    public String selectProvinceName(@RequestParam("provinceId") String provinceId);
}
