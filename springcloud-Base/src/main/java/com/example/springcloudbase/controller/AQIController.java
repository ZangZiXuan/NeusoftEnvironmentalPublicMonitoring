package com.example.springcloudbase.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.AQI;
import com.example.springcloudbase.mapper.AQIMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AQI findAqiDetails(@RequestBody int aqiLevel) {
        AQI aqi = aqiMapper.selectOne(Wrappers.<AQI>lambdaQuery().eq(AQI::getAqiId, aqiLevel));
        if(aqi!=null) {
            return aqi;
        }else {
            return null;
        }
    }


}
