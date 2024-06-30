package com.example.springcloudmessagepublic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/27 9:50
 * @Version 1.0
 */
//name = "springcloud-api-cities",
@FeignClient(name = "springcloud-api",url = "localhost:8081")
public interface CitiesFeignService {
    @PostMapping("/city/selectCity")
    public Map<String,Object> selectCity(@RequestBody String cityId);

    @PostMapping("/province/selectProvince")
    public Map<String,Object> selectProvince(@RequestBody String provinceId);
    @GetMapping("/city/selectCityName")
    public String selectCityName(@RequestParam("cityId") String cityId);


}
