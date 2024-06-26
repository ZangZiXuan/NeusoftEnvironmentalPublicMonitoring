package com.example.springcloudmessagegriddler.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.MessageGriddler;
import com.example.springcloudapi.dao.dto.MessageGriddlerDTO;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.mapper.ProvinceMapper;
import com.example.springcloudapi.utils.CommUtil;
import com.example.springcloudmessagegriddler.mapper.MessageGriddlerMapper;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 11:29
 * @Version 1.0
 */
@RestController
@RequestMapping("/messageGriddler")
public class MessageGriddlerController {
    @Autowired
    MessageGriddlerMapper messageGriddlerMapper;

    ProvinceMapper provinceMapper;

    MessagePublicMapper messagePublicMapper;
    /**
     * 网格员确认提交数据
     */

    @PostMapping("/creatMessageGriddler")
    public Map<String,Object> creatMessageGriddler(@RequestParam("co") int co,
            @RequestParam("pm") int pm,@RequestParam("so2") int so2,
            @RequestParam("message_public_id") String messagePublicId) {
        MessageGriddler messageGriddler = new MessageGriddler(
                UUID.randomUUID().toString(),messagePublicId,so2,co,pm,
                CommUtil.getNowDateLongStr("yyyy-mm-dd HH:mm:ss"),1
        );
        int insert = messageGriddlerMapper.insert(messageGriddler);
        Map<String, Object> response = new HashMap<>();
        if(insert == 1){
            response.put("success", true);
            response.put("message", "网格员端的提交实测数据添加成功");
            response.put("data",insert);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "网格员端的提交实测数据添加失败");
            response.put("data",null);
            return response;
        }
    }

    /**
     * 查看网格员确认的所有信息
     * @return Map
     */

    @GetMapping("/viewAllMessageGriddler")
    public Map<String,Object> viewAllMessageGriddler() {
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(null);

        Map<String, Object> response = new HashMap<>();

        if(!messageGriddlerList.isEmpty()){
            response.put("success", true);
            response.put("message", "查看所有的网格员端的提交实测数据成功");
            response.put("data",messageGriddlerList);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "查看所有的网格员端的提交实测数据失败");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }
    /**
     * 管理员端
     * 省分组分项检查统计
     *  Provincial subgroup sub-inspection statistics
     * @Param null
     * return ResponseEntity
     */

    @RequestMapping("/viewProvinceSubgroup")
    public Map<String, Object> viewProvinceSubgroup() {
        // 获取所有的MessageGriddler记录
        List<MessageGriddler> messageGriddlers = messageGriddlerMapper.selectList(
                Wrappers.<MessageGriddler>lambdaQuery().eq(MessageGriddler::getStatus, 1)
        );

        // 用于存储返回的数据
        HashMap<String, Object> response = new HashMap<>();
        // 用于存储统计结果
        Map<String, MessageGriddlerDTO> provinceStats = new HashMap<>();

        for (MessageGriddler messageGriddler : messageGriddlers) {
            MessagePublic messagePublic = messagePublicMapper.selectById(messageGriddler.getMessagePublicId());
            Province province = provinceMapper.selectById(messagePublic.getProvinceId());

            String provinceId = province.getId();
            MessageGriddlerDTO stats = provinceStats.getOrDefault(provinceId, new MessageGriddlerDTO(
                    provinceId,
                    province.getProvinceName(),
                    province.getShortTitle(),
                    0, 0, 0, 0
            ));

            int co = messageGriddler.getCo();
            int pm = messageGriddler.getPm();
            int so2 = messageGriddler.getSo2();

            if (co > 24) stats.setCoNum(stats.getCoNum() + 1);
            if (pm > 150) stats.setPmNum(stats.getPmNum() + 1);
            if (so2 > 800) stats.setSoNum(stats.getSoNum() + 1);
            stats.setAQINum(Math.max(Math.max(stats.getCoNum(),stats.getPmNum()),stats.getSoNum()));

            provinceStats.put(provinceId, stats);
        }

        response.put("provinceStats", new ArrayList<>(provinceStats.values()));
        if(provinceStats.isEmpty()) {
            response.put("success", false);
            response.put("message", "查看省分组分项检查统计数据失败");
            response.put("data",null);
            return response;
        } else {
            response.put("provinceStats", new ArrayList<>(provinceStats.values()));
            response.put("data",provinceStats);
            response.put("success", true);
            response.put("message", "查看省分组分项检查统计数据成功");
            return response;
        }
    }
}

