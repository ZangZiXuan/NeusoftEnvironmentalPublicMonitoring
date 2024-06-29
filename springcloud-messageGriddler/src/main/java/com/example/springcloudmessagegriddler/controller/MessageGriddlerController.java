package com.example.springcloudmessagegriddler.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.AQIDTO;
import com.example.springcloudapi.dao.entity.*;
import com.example.springcloudapi.dao.dto.MessageGriddlerDTO;
import com.example.springcloudapi.mapper.ProvinceMapper;
import com.example.springcloudapi.utils.CommUtil;
import com.example.springcloudmessagegriddler.feign.CitiesFeignService;
import com.example.springcloudmessagegriddler.feign.MessagePublicFeignService;

import com.example.springcloudmessagegriddler.mapper.MessageGriddlerMapper;

import com.example.springcloudmessagegriddler.service.AQIService;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    MessagePublicFeignService messagePublicFeignService;
    @Autowired
    CitiesFeignService citiesFeignService;

    /**
     * 网格员确认提交数据
     */

    @PostMapping("/creatMessageGriddler")
    public Map<String,Object> creatMessageGriddler(@RequestParam("co") int co,
            @RequestParam("pm") int pm,@RequestParam("so2") int so2,
            @RequestParam("message_public_id") String messagePublicId,@RequestParam("aqi_level") int aqiLevel) {
        MessageGriddler messageGriddler = new MessageGriddler(
                UUID.randomUUID().toString(),messagePublicId,so2,co,pm,
                CommUtil.getNowDateLongStr("yyyy-mm-dd HH:mm:ss"),1,aqiLevel
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
     * 以下均为统计信息模块
     */
    /**
     * 管理员端
     * 省分组分项检查统计
     *  Provincial subgroup sub-inspection statistics
     * @Param null
     * return ResponseEntity
     */

    @GetMapping("/viewProvinceSubgroup")
    public Map<String, Object> viewProvinceSubgroup() {
        // 获取所有的MessageGriddler记录
        List<MessageGriddler> messageGriddlers = messageGriddlerMapper.selectList(
                Wrappers.<MessageGriddler>lambdaQuery().eq(MessageGriddler::getStatus, 1)
        );
        System.out.println(messageGriddlers);
        // 用于存储返回的数据
        HashMap<String, Object> response = new HashMap<>();
        // 用于存储统计结果
        Map<String, MessageGriddlerDTO> provinceStats = new HashMap<>();

        for (MessageGriddler messageGriddler : messageGriddlers) {

            Object messagePublic1 = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            MessagePublic messagePublic = objectMapper.convertValue(messagePublic1, MessagePublic.class);

            Object province1 = citiesFeignService.selectProvince(messagePublic.getProvinceId()).get("data");
            ObjectMapper objectMapper2 = new ObjectMapper();
            Province province = objectMapper2.convertValue(province1, Province.class);

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
    /**
     * 管理员端
     * 统计信息管理
     * AQI空气质量指数级别分布
     *
     */
    @RequestMapping("/viewAQILevel")
    public Map<String,Object> ViewAQILevel() {
        ArrayList<AQIDTO> list = new ArrayList<>();
        HashMap<String, Object> response = new HashMap<>();
        for(int i = 1;i <=6;i++) {
            int num = Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                    .eq(MessageGriddler::getAqiLevel, i)
                    .eq(MessageGriddler::getStatus, 1)).toString());
            AQI aqiDetails = citiesFeignService.findAqiDetails(String.valueOf(i));
            AQIDTO aqidto = new AQIDTO(i, aqiDetails.getDescription(), num);
            list.add(aqidto);
        }

        Map<String, Integer> result = Map.of("one", list.get(0).getCountNum(),"two",list.get(1).getCountNum()
                , "three", list.get(2).getCountNum(),
                "four", list.get(3).getCountNum(), "five",list.get(4).getCountNum(),
                "six", list.get(5).getCountNum());
        if(list.isEmpty()) {
            response.put("data",null);
            response.put("success",true);
            response.put("result",result);
            response.put("message","统计AQI空气质量指数级别分布成功");
            return response;
        }else {
            response.put("data",list);
            response.put("success",false);
            response.put("result",null);
            response.put("message","统计AQI空气质量指数级别分布失败");
            return response;
        }
    }

    /**
     *
     * 管理员端
     * AQI空气质量指数超标数量统计表
     * @return
     */
    @GetMapping("/AqiLevelOver")
    public Map<String,Object> AqiLevelOver() {
        List<MessageGriddler> list = messageGriddlerMapper.selectList(Wrappers.<MessageGriddler>lambdaQuery()
                .gt(MessageGriddler::getAqiLevel, 3)
                .select(MessageGriddler::getTime));

        // 通过 Java 流处理逐月分组和统计
        Map<String, Long> result = list.stream()
                .collect(Collectors.groupingBy(
                        mg -> String.format("%d-%02d", mg.getTime().getYear() + 1900, mg.getTime().getMonth() + 1),
                        Collectors.counting()
                ));

        HashMap<String, Object> response = new HashMap<>();
        if(!result.isEmpty()) {
            response.put("data",result);
            response.put("success",true);
            response.put("message","获取到逐月的分组和统计成功");
            return response;
        }else {
            response.put("data",null);
            response.put("success",false);
            response.put("message","获取到逐月的分组和统计失败");
            return response;
        }
    }
    @GetMapping("/elseData")
    public Map<String,Object> ElseData() {
        int sumTest = Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                .eq(MessageGriddler::getStatus, 1)).toString());
        int goodLevel = Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                .eq(MessageGriddler::getStatus, 1)
                        .lt(MessageGriddler::getAqiLevel,2)
                ).toString());

        Map<String, Object> result = new HashMap<>();
        HashMap<String, Object> response = new HashMap<>();
        result.put("空气质量检测总数量",sumTest);
        result.put("空气质量检测良好数量",goodLevel);
//        List<MessageGriddler> list = messageGriddlerMapper.selectList(Wrappers.<MessageGriddler>lambdaQuery()
//                .eq(MessageGriddler::getStatus, 1));
//        for(MessageGriddler messageGriddler:list) {
//            Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
//            ObjectMapper objectMapper = new ObjectMapper();
//            MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);
////            @PostMapping("/city/selectCity")
////    public Map<String,Object> selectCity(@RequestBody String cityId);
//            Object data1 = citiesFeignService.selectCity(messagePublic.getCityId()).get("data");
//            ObjectMapper objectMapper2 = new ObjectMapper();
//            City c = objectMapper2.convertValue(data, City.class);
//            if(c.getIsCapitalCity()==1) {
//
//            }
//        }
        List<String> allCapitalCity = citiesFeignService.findAllCapitalCity();
        List<String> allAddressByCity = messagePublicFeignService.findAllAddressByCity();
        int countCapitalCity = countCommonElements(allCapitalCity, allAddressByCity);
        double capitalCityLevel = countCapitalCity / 34;
        result.put("省会城市网格覆盖范围",capitalCityLevel);
        List<String> allBigCity = citiesFeignService.findAllBigCity();
        int countBigCity = countCommonElements(allBigCity, allAddressByCity);
        double bigCityLevel = countBigCity / 391;
        result.put("大城市覆盖范围",bigCityLevel);
        if(result.isEmpty()) {
            response.put("data",null);
            response.put("success",false);
            response.put("message","获取其他统计信息失败");
            return response;
        } else {
            response.put("data",result);
            response.put("success",true);
            response.put("message","获取其他统计信息成功");
            return response;
        }
    }
    // 方法计算两个列表中相同字符串的数量
    public int countCommonElements(List<String> list1, List<String> list2) {
        // 使用Set来存储列表中的字符串，自动去重
        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);

        // 保留set1和set2的交集
        set1.retainAll(set2);

        // 交集的大小就是两个列表中相同字符串的数量
        return set1.size();
    }
}

