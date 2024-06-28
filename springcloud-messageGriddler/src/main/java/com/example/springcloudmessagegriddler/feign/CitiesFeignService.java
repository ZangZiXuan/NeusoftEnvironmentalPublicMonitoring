package com.example.springcloudmessagegriddler.feign;

import com.example.springcloudapi.dao.entity.AQI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 15:08
 * @Version 1.0
 */
@FeignClient(name = "springcloud-api",url = "localhost:8081")
public interface CitiesFeignService {
    @PostMapping("/city/selectCity")
    public Map<String,Object> selectCity(@RequestBody String cityId);
    @PostMapping("/province/selectProvince")
    public Map<String,Object> selectProvince(@RequestBody String provinceId);
    @PostMapping("/aqi/findAqiDetails")
    public AQI findAqiDetails(@RequestBody String aqiLevel);
}
