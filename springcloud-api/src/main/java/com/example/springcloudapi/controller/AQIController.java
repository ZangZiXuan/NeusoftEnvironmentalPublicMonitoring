package com.example.springcloudapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.AQI;
import com.example.springcloudapi.mapper.AQIMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/28 17:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/aqi")
public class AQIController {

    @Autowired
    AQIMapper aqiMapper;
    @PostMapping("/findAqiDetails")
    public AQI findAqiDetails(@RequestBody String aqiLevel) {
        AQI aqi = aqiMapper.selectOne(Wrappers.<AQI>lambdaQuery().eq(AQI::getAqiId, aqiLevel));
        if(aqi!=null) {
            return aqi;
        }else {
            return null;
        }
    }


}
