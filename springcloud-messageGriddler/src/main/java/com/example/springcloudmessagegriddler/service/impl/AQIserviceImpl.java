package com.example.springcloudmessagegriddler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springcloudapi.dao.dto.AQIDTO;
import com.example.springcloudapi.dao.entity.AQI;
import com.example.springcloudapi.dao.entity.MessageGriddler;
import com.example.springcloudmessagegriddler.mapper.AQIMapper;
import com.example.springcloudmessagegriddler.mapper.MessageGriddlerMapper;
import com.example.springcloudmessagegriddler.service.AQIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/27 11:36
 * @Version 1.0
 */
@Service
public class AQIserviceImpl implements AQIService {

    @Autowired
    private MessageGriddlerMapper messageGriddlerMapper;

    @Autowired
    private AQIMapper aqiMapper;

    public List<AQIDTO>  getAQIDistribution() {
        QueryWrapper<MessageGriddler> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("aqi_level", "COUNT(*) as countNum");
        queryWrapper.groupBy("aqi_level");

        List<MessageGriddler> messageList = messageGriddlerMapper.selectList(queryWrapper);

        return messageList.stream().map(message -> {
            AQIDTO dto = new AQIDTO();
            dto.setAqiLevel(message.getAqiLevel());
            AQI aqi = aqiMapper.selectById(message.getAqiLevel());
            dto.setDescription(aqi != null ? aqi.getDescription() : "");
            dto.setCountNum(message.getCountNum());
            return dto;
        }).collect(Collectors.toList());
    }
}
