package com.example.springcloudmessagegriddler.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.AQIDTO;
import com.example.springcloudapi.dao.dto.PlaceDTO;
import com.example.springcloudapi.dao.entity.*;
import com.example.springcloudapi.dao.dto.MessageGriddlerDTO;
import com.example.springcloudapi.mapper.ProvinceMapper;
import com.example.springcloudapi.utils.CommUtil;
import com.example.springcloudapi.utils.HttpResponseEntity;
import com.example.springcloudmessagegriddler.dto.DigitalScreenMessageGriddler;
import com.example.springcloudmessagegriddler.feign.CitiesFeignService;
import com.example.springcloudmessagegriddler.feign.MessagePublicFeignService;

import com.example.springcloudmessagegriddler.feign.PublicFeignService;
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

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            @RequestParam("message_public_id") String messagePublicId
            ,@RequestParam("aqi_level") int aqiLevel,
            @RequestParam("griddler_id") String griddlerId){
        MessageGriddler messageGriddler = new MessageGriddler(
                UUID.randomUUID().toString(),messagePublicId,griddlerId,so2,co,pm,
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
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(Wrappers.<MessageGriddler>lambdaQuery().eq(MessageGriddler::getStatus,1));

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

//        result.put("pm25", pm25Count);
//        result.put("so2", so2Count);
//        result.put("co", coCount);
//        result.put("aqi", AQICount);

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
            response.put("success",false);
            response.put("result",result);
            response.put("message","统计AQI空气质量指数级别分布失败");
            return response;
        }else {
            response.put("data",list);
            response.put("success",true);
            response.put("result",result);
            response.put("message","统计AQI空气质量指数级别分布成功");
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
        double capitalCityLevel = countCapitalCity / 34.0;
        result.put("省会城市网格覆盖范围",capitalCityLevel);
        List<String> allBigCity = citiesFeignService.findAllBigCity();
        int countBigCity = countCommonElements(allBigCity, allAddressByCity);
        double bigCityLevel = countBigCity / 391.0;
        result.put("大城市覆盖范围",bigCityLevel);
        Map<String, String> result1 = Map.of(
                "provinces", String.format("%.2f", capitalCityLevel * 100),
                "cities", String.format("%.2f", bigCityLevel * 100));


        if(result.isEmpty()) {
            response.put("data",null);
            response.put("success",false);
            response.put("result",null);
            response.put("message","获取其他统计信息失败");
            return response;
        } else {
            response.put("data",result);
            response.put("success",true);
            response.put("result",result1);
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



    @GetMapping("/getProcession")
    public HttpResponseEntity getProcession() {
        Map<String, Integer> result = new HashMap<>();
        int countMessagePublic = messagePublicFeignService.countMessagePublic();
        int countAlreadyAssigned = messagePublicFeignService.countAlreadyAssigned();
        int countUnGriddler = messagePublicFeignService.countUnGriddler();
        int submission = Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                .eq(MessageGriddler::getStatus, 1)).toString());
        result.put("report",countMessagePublic);
        result.put("task",countAlreadyAssigned);
        result.put("submission",submission);
        return HttpResponseEntity.response(true, "get procession", result);
    }
    @Autowired
    PublicFeignService publicFeignService;

    @GetMapping("/digitalScreenShowMessageGriddler")
    public HttpResponseEntity digitalScreenShowMessageGriddler(@RequestParam("limitNum") Integer num ) {
        if(num <= 0) return HttpResponseEntity.error("限制条数出现错误，请检查后重试");
        QueryWrapper<MessageGriddler> queryWrapper = new QueryWrapper<>();
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(queryWrapper.orderByDesc("time"));
        List<DigitalScreenMessageGriddler> result = new ArrayList<>();
        boolean success = !messageGriddlerList.isEmpty();
        if(success) {
            int count = 0;
            for(MessageGriddler messageGriddler: messageGriddlerList) {
                if(count >= num) {
                    break;
                }else {
                    System.out.println(messageGriddler.getMessagePublicId());
                    Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
                    System.out.println("data:"+data);
                    ObjectMapper objectMapper = new ObjectMapper();
                    MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);
                    System.out.println(messagePublic);
                    Object data1 = citiesFeignService.selectCity(messagePublic.getCityId()).get("data");
                    ObjectMapper objectMapper1 = new ObjectMapper();
                    PlaceDTO placeDTO = objectMapper1.convertValue(data1, PlaceDTO.class);
                    System.out.println(placeDTO);
                    Object data2 = citiesFeignService.selectProvince(placeDTO.getProvinceId()).get("data");
                    ObjectMapper objectMapper2 = new ObjectMapper();
                    Province province = objectMapper2.convertValue(data2, Province.class);
                    System.out.println(province);
                    DigitalScreenMessageGriddler digitalScreenMessageGriddler = new DigitalScreenMessageGriddler(messageGriddler.getGriddlerId(), messageGriddler.getAqiLevel()
                            , messageGriddler.getTime(), province.getProvinceName(), citiesFeignService.selectCityName(placeDTO.getCityId()),
                            messagePublic.getAddress());

                    result.add(digitalScreenMessageGriddler);
                    count++;
                }
            }
        }
        return HttpResponseEntity.response(success,"query",result);
    }
    /**
     * 中国地图
     */
    private Map<String,Integer> AQIRegionalDistribution() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusWeeks(1);
        QueryWrapper<MessageGriddler> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("time",oneWeekAgo,now);
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(queryWrapper.orderByDesc("aqi_level"));
        HashMap<String, Integer> maxAqiByProvince = new HashMap<>();
        for(MessageGriddler messageGriddler:messageGriddlerList){
            Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);
            System.out.println(messagePublic);
            Object data1 = citiesFeignService.selectProvince(messagePublic.getProvinceId()).get("data");
            ObjectMapper objectMapper1 = new ObjectMapper();
            Province province1 = objectMapper1.convertValue(data1, Province.class);
            String province = province1.getProvinceName();
            System.out.println(province);
            if(maxAqiByProvince.containsKey(province)) {
                if(messageGriddler.getAqiLevel() > maxAqiByProvince.get(province)) {
                    maxAqiByProvince.put(province,messageGriddler.getAqiLevel());
                }
            } else {
                maxAqiByProvince.put(province,messageGriddler.getAqiLevel());
            }
        }
        return maxAqiByProvince;
    }

    /**
     * 各省地图
     */
    private Map<String, Integer> getWeeklyAirData_Province(String province) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusWeeks(1);
        List<String> citiesByProvinceId = citiesFeignService.getCitiesByProvinceId(province);
        QueryWrapper<MessageGriddler> queryWrapper = new QueryWrapper<>();
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(
                queryWrapper.between("time", oneWeekAgo, now));

        List<String> messagePublicIds = messageGriddlerList.stream()
                .map(MessageGriddler::getMessagePublicId)
                .collect(Collectors.toList());
        List<MessagePublic> messagePublicList = new ArrayList<>();
        for(String messagePublicId:messagePublicIds){
            Object data = messagePublicFeignService.selectMessagePublic(messagePublicId).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);
            messagePublicList.add(messagePublic);
//            messagePublic.getCityId()
        }

    // 创建一个映射表，以便快速查找 message_public_id 对应的 city_id
    Map<String, String> messagePublicIdToCityIdMap = messagePublicList.stream()
            .collect(Collectors.toMap(MessagePublic::getId, MessagePublic::getCityId));

    // 过滤 messageGriddlerList，保留 city_id 在 citiesByProvinceId 列表中的记录

List<MessageGriddler> filteredMessageGriddlerList = messageGriddlerList.stream()
        .filter(griddler -> messagePublicIdToCityIdMap.containsKey(griddler.getMessagePublicId()) &&
                citiesByProvinceId.contains(messagePublicIdToCityIdMap.get(griddler.getMessagePublicId())))
        .collect(Collectors.toList());

// 按 aqi_level 排序
filteredMessageGriddlerList.sort(Comparator.comparing(MessageGriddler::getAqiLevel).reversed());

//        messageGriddlerMapper.selectList(Wrappers.<MessageGriddler>lambdaQuery().eq()
        Map<String, Integer> maxAqiByCity = new HashMap<>();
        for(MessageGriddler messageGriddler: messageGriddlerList) {
            Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);
            String city = citiesFeignService.selectCityName(messagePublic.getCityId());
            if(maxAqiByCity.containsKey(city)) {
                if(messageGriddler.getAqiLevel()>maxAqiByCity.get(city)) {
                    maxAqiByCity.put(city,messageGriddler.getAqiLevel());
                }
            } else {
                maxAqiByCity.put(city,messageGriddler.getAqiLevel());
            }
        }
        return maxAqiByCity;
    }


   @GetMapping("/AQIRegionalDistributionChina")
    public HttpResponseEntity AQIRegionalDistributionChina(@RequestParam("province") String province) {
       System.out.println(province);
        Map<String, Integer> data = null;
        if(province.equals("china"))
            data = AQIRegionalDistribution();
        else
            data = getWeeklyAirData_Province(province);
        List<Map<String, Object>> result = new ArrayList<>();
        for(String key : data.keySet())
            result.add(Map.of("name", key, "value", data.get(key)));
        return HttpResponseEntity.success("get weekly air data", result);
    }

    /**
     * 待做任务列表
     */
    @GetMapping("/viewAllMessageGriddlerUndo")
    public Map<String,Object> viewMessageGriddlerUndo(@RequestParam("griddlerId") String griddler) {
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.
                selectList(Wrappers.<MessageGriddler>lambdaQuery().
                        eq(MessageGriddler::getStatus,0)
                        .eq(MessageGriddler::getGriddlerId,griddler));

        Map<String, Object> response = new HashMap<>();

        if(!messageGriddlerList.isEmpty()){
            response.put("success", true);
            response.put("message", "查看所有的网格员端待做");
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
     * 统计各个地区出现优的概率
     * 找到空气质量最好的10个城市
     *
     */
    @GetMapping("/selectTopCity")
    public HttpResponseEntity selectTopCity(@RequestParam("limitNum") Integer limitNum) {
        if(limitNum == null || limitNum < 1){
            return HttpResponseEntity.error("limitNum is not valid");
        }
        // 获取当前时间的前一周时间
        LocalDateTime oneWeekAgo = LocalDateTime.now().minus(1, ChronoUnit.WEEKS);
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(Wrappers.<MessageGriddler>lambdaQuery()
                .eq(MessageGriddler::getStatus, 1)
                .ge(MessageGriddler::getMessagePublicId, oneWeekAgo)
                .orderByAsc(MessageGriddler::getCo)
                .orderByAsc(MessageGriddler::getPm)
                .orderByAsc(MessageGriddler::getSo2)
                .last("LIMIT " + limitNum));
        ArrayList<PlaceDTO> resultList = new ArrayList<>();
        for(MessageGriddler messageGriddler:messageGriddlerList) {
            Object data = messagePublicFeignService.selectMessagePublic(messageGriddler.getMessagePublicId()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);

            Object data1 = citiesFeignService.selectProvince(messagePublic.getProvinceId()).get("data");
            ObjectMapper objectMapper1 = new ObjectMapper();
            Province province = objectMapper1.convertValue(data1, Province.class);
            province.getProvinceName();
            PlaceDTO placeDTO = new PlaceDTO(province.getShortTitle(), province.getProvinceName(), citiesFeignService.selectCityName(messagePublic.getCityId()));
            resultList.add(placeDTO);
        }

        if (resultList.isEmpty()) {
            return HttpResponseEntity.error("获取空气质量最好的10个城市失败");
        } else {
            return HttpResponseEntity.success("query ", resultList);
        }
    }

//        List<Report> reportList = reportService.list(queryWrapper.orderByDesc("created_time"));
//        List<ResponseReportEntity> result = new ArrayList<>();
//        for(int i = 0; i < limitNum && i < reportList.size(); i++){
//            City city = cityService.getCityById(reportList.get(i).getCityId());
//            result.add(new ResponseReportEntity(reportList.get(i), city));
//        }
//        return HttpResponseEntity.success("query ", result);
//    }
/**
 * 数据大屏显示AQI,SO2,CO,PM2.5超标统计
 * 按各个省份进行筛选
 * @return 每个省份的超标统计情况
 */

    @GetMapping("/getProvinceCount")
    public HttpResponseEntity getProvinceCount_digitalScreen() {
        int level = 3;
        List<String> provinces = citiesFeignService.selectProvinceId();

        Integer[] pm25Count = new Integer[provinces.size()];
        Integer[] so2Count = new Integer[provinces.size()];
        Integer[] coCount = new Integer[provinces.size()];
        Integer[] AQICount = new Integer[provinces.size()];
        for(int i = 0;i < provinces.size();i++) {
            List<String> citiesIdByProvinceId = citiesFeignService.getCitiesIdByProvinceId(provinces.get(i));
            QueryWrapper<MessageGriddler> QueryWrapper = new QueryWrapper<>();
            QueryWrapper.ge("aqi_level",level);
            List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(QueryWrapper);
            List<String> messagePublicIds = messageGriddlerList.stream()
                    .map(MessageGriddler::getMessagePublicId)
                    .collect(Collectors.toList());
            ArrayList<MessagePublic> messagePublicList = new ArrayList<>();
            for(String messagePublicId:messagePublicIds) {
                Object data = messagePublicFeignService.selectMessagePublic(messagePublicId).get("data");
                ObjectMapper objectMapper = new ObjectMapper();
                MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);
                messagePublicList.add(messagePublic);
            }
            Map<String,String> messagePublicIdToCityIdMap = messagePublicList.stream()
                    .collect(Collectors.toMap(MessagePublic::getId,MessagePublic::getCityId));
//            保留city_id在citiesIdByProvinceId列表中的记录
            List<MessageGriddler> messageGriddlerList1 = messageGriddlerList.stream()
                    .filter(griddler -> messagePublicIdToCityIdMap.containsKey(griddler.getMessagePublicId()) &&
                            citiesIdByProvinceId.contains(messagePublicIdToCityIdMap.get(griddler.getMessagePublicId())))
                    .collect(Collectors.toList());

            AQICount[i] = messageGriddlerList1.size();
            so2Count[i]= Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                    .ge(MessageGriddler::getSo2, 186)).toString());
            coCount[i]= Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                    .ge(MessageGriddler::getCo, 36)).toString());
            pm25Count[i]= Integer.parseInt(messageGriddlerMapper.selectCount(Wrappers.<MessageGriddler>lambdaQuery()
                    .ge(MessageGriddler::getPm, 116)).toString());
        }
        Map<String,  Object[]> result = new HashMap<>();
        result.put("category", citiesFeignService.selectProvince().toArray());
        result.put("pm25", pm25Count);
        result.put("so2", so2Count);
        result.put("co", coCount);
        result.put("aqi", AQICount);
        return HttpResponseEntity.success("get province count", result);
    }
    }


