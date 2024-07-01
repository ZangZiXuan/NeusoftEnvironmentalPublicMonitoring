package com.example.springcloudmessagepublic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.PlaceDTO;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.dto.MessagePublicDTO;
import com.example.springcloudapi.dao.entity.City;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudapi.mapper.CityMapper;
import com.example.springcloudapi.mapper.ProvinceMapper;
import com.example.springcloudapi.utils.HttpResponseEntity;
import com.example.springcloudapi.utils.UUIDUtil;
import com.example.springcloudmessagepublic.dto.DigitalMessagePublicDTO;
import com.example.springcloudmessagepublic.dto.ViewAllMessagePublicDataFrame;
import com.example.springcloudmessagepublic.feign.CitiesFeignService;

import com.example.springcloudmessagepublic.feign.PublicFeignService;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 9:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/messagePublic")
public class MessagePublicController {
    @Autowired
    MessagePublicMapper messagePublicMapper;
    @Autowired
    CitiesFeignService citiesFeignService;
    @Autowired
    PublicFeignService publicFeignService;
    /**
     * 公众监督员端的提交
     * @param messagePublic
     * @return Map
     */
    @PostMapping("/submitMessagePublic")
    public Map<String, Object> submitMessagePublic(@RequestBody MessagePublic messagePublic) {
        Map<String, Object> response = new HashMap<>();
        messagePublic.setId(UUID.randomUUID().toString());
        int insert = messagePublicMapper.insert(messagePublic);
        if(insert == 1){
            response.put("success", true);
            response.put("message", "公众监督员端的提交添加成功");
            response.put("data",insert);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "公众监督员端的提交添加失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            response.put("data",null);
            return response;
        }
    }

    /**
     * 查找特定id的本人提交的反馈信息
     * @param publicId
     * @return ResponseEntity
     */

    @GetMapping("/ViewMyMessagePublic/{publicId}")
    public Map<String,Object> ViewMyMessagePublic(@PathVariable("publicId") String publicId) {
        Map<String, Object> response = new HashMap<>();

        List<MessagePublic> messagePublicList = messagePublicMapper.selectList
                (Wrappers.<MessagePublic>lambdaQuery().eq(MessagePublic::getPublicId, publicId));
        List<MessagePublicDTO> messagePublicDTOList = new ArrayList<>();
        if(!messagePublicList.isEmpty()){
            for (MessagePublic messagePublic :
                    messagePublicList) {
                Object data = citiesFeignService.selectCity(messagePublic.getCityId()).get("data");
                System.out.println(data);
                ObjectMapper objectMapper = new ObjectMapper();
                PlaceDTO placeDTO = objectMapper.convertValue(data, PlaceDTO.class);
                System.out.println(placeDTO);

                Object data1 = citiesFeignService.selectProvince(placeDTO.getProvinceId()).get("data");
                ObjectMapper objectMapper1 = new ObjectMapper();
                Province province = objectMapper1.convertValue(data1, Province.class);
                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                  null,messagePublic,province.getProvinceName(),citiesFeignService.selectCityName(placeDTO.getCityId()), placeDTO.getShortTitle());
                messagePublicDTOList.add(messagePublicDTO);
            }
            response.put("success", true);
            response.put("message", "查询当前公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }

    /**
     * 查看所有的用户的所有反馈信息
     * @return map
     */
    @PostMapping("/viewAllMessagePublic")
    public Map<String,Object> viewAllMessagePublic(){
        Map<String, Object> response = new HashMap<>();
//        List<ViewAllMessagePublicDataFrame> finalResult = new ArrayList<>();
        List<MessagePublic> messagePublicList = messagePublicMapper.selectList(null);
        List<MessagePublicDTO> messagePublicDTOList = new ArrayList<>();
        if(!messagePublicList.isEmpty()) {
            for (MessagePublic messagePublic :
                    messagePublicList) {
                String publicId = messagePublic.getPublicId();

                /**
                 * 从查询public的具体信息
                 */
                Public publicDetail = publicFeignService.getPublicById(publicId);
                System.out.println(publicDetail);
//City city = (City)citiesFeignService.selectCity(messagePublic.getCityId());
//                Province province = (Province) provinceFeignService.selectProvince(city.getProvinceId());
                String city = citiesFeignService.selectCityName(messagePublic.getCityId()).toString();
                System.out.println(city);
                Object data1 = citiesFeignService.selectProvince(messagePublic.getProvinceId()).get("data");
                System.out.println(data1);
                ObjectMapper objectMapper1 = new ObjectMapper();
                Province province = objectMapper1.convertValue(data1, Province.class);

                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                        publicDetail,messagePublic, province.getProvinceName(),
                        city, province.getShortTitle());

                messagePublicDTOList.add(messagePublicDTO);
//                String[] s1 = messagePublic.getDate().toString().split(" ");

//                finalResult.add(new ViewAllMessagePublicDataFrame(messagePublic.getId(),
//                        publicDetail.getName(), province.getProvinceName(),
//                        city,messagePublic.getLevel(),s1[0],s1[1]));
            }
            response.put("success", true);
            response.put("message", "查询所有的公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
//            response.put("result",finalResult);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }
    @GetMapping("/selectMessagePublicDigitalScreen")
    public HttpResponseEntity selectMessagePublicDigitalScreen(@RequestParam("limitNum") Integer limitNum) {
        if(limitNum == null || limitNum < 1) return HttpResponseEntity.error("限制的条数不合法请稍后再试");
        QueryWrapper<MessagePublic> queryWrapper = new QueryWrapper<>();
        List<MessagePublic> messagePublicList = messagePublicMapper.selectList(queryWrapper.orderByDesc("date"));
        ArrayList<DigitalMessagePublicDTO> result = new ArrayList<>();
        for(MessagePublic messagePublic:messagePublicList) {
            Object data = citiesFeignService.selectProvince(messagePublic.getProvinceId()).get("data");
            ObjectMapper objectMapper = new ObjectMapper();
            Province province = objectMapper.convertValue(data, Province.class);
            String provinceName = province.getProvinceName();
            String cityName = citiesFeignService.selectCityName(messagePublic.getCityId());
            String address = messagePublic.getAddress();
            Date date = messagePublic.getDate();
            String publicId = messagePublic.getPublicId();
            Integer aqiLevel = messagePublic.getLevel();
            result.add(new DigitalMessagePublicDTO(provinceName,cityName,address,date,publicId,aqiLevel,messagePublic.getDescription()));
        }
        return HttpResponseEntity.success("query ", result);
    }

//        List<ResponseReportEntity> result = new ArrayList<>();
//        for(int i = 0; i < limitNum && i < reportList.size(); i++){
//            City city = cityService.getCityById(reportList.get(i).getCityId());
//            result.add(new ResponseReportEntity(reportList.get(i), city));
//        }
//        return HttpResponseEntity.success("query ", result);
//    }
    /**
     * 通过特定的筛选条件，找到符合要求的信息
     * @param provinceId
     * @param cityId
     * @param level
     * @param date
     * @param status
     * @return ResponseEntity
     */
    @RequestMapping("/viewSomeMessagePublic")
    public Map<String,Object> viewSomeMessagePublic(@RequestParam("provinceId") String provinceId,
                                                                    @RequestParam("cityId") String cityId,
                                                                    @RequestParam("level") String level,
                                                                    @RequestParam("Date") Date date,
                                                                    @RequestParam("status") Integer status) {
        HashMap<String, Object> response = new HashMap<>();
        List<MessagePublicDTO> messagePublicDTOList = new ArrayList<>();

        List<MessagePublic> messagePublicList = messagePublicMapper.selectList(Wrappers.<MessagePublic>lambdaQuery()
                .eq(MessagePublic::getCityId, cityId)
                .eq(MessagePublic::getProvinceId, provinceId)
                .eq(MessagePublic::getLevel, level)
                .eq(MessagePublic::getDate, date)
                .eq(MessagePublic::getStatus, status)
        );

        if(!messagePublicList.isEmpty()) {
            for (MessagePublic messagePublic :
                    messagePublicList) {
                City city = (City)citiesFeignService.selectCity(messagePublic.getCityId());
                Province province = (Province) citiesFeignService.selectProvince(city.getProvinceId());

                String publicId = messagePublic.getPublicId();
                /**
                 * 从查询public的具体信息
                 */
                Public publicDetail = publicFeignService.getPublicById(publicId);

                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                    publicDetail,messagePublic, province.getProvinceName(), city.getCityName(), province.getShortTitle());
                messagePublicDTOList.add(messagePublicDTO);
            }
            response.put("success", true);
            response.put("message", "用特定条件查询特定的公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "没有符合筛选条件的公众监督员的提交记录");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }

    /**
     *
     * @param pubicId
     * 注意这里面是所有的id，（唯一识别符号）
     * @return
     */
    @PostMapping("/selectMessagePublic")
    public Map<String,Object> selectMessagePublic(@RequestParam("publicId") String pubicId) {
        HashMap<String, Object> response = new HashMap<>();
        List<MessagePublic> messagePublics = messagePublicMapper.selectList(Wrappers.<MessagePublic>lambdaQuery().eq(MessagePublic::getId, pubicId));


        if(messagePublics.isEmpty()) {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;

        }else {
            response.put("success", true);
            response.put("message", "某个id的公众监督员记录");
            response.put("data",messagePublics.get(0));
            return response;
        }
    }


    /**
     * 统计反馈信息来自的地区
     *
     */
    @GetMapping("/findAllAddressByCityId")
    public List<String> findAllAddressByCity() {
        List<String> list = messagePublicMapper.selectList(null)
                .stream()
                .map(MessagePublic::getCityId)
                .collect(Collectors.toList());

        return list;
    }

    /**
     * 全部的信息记录条数
     * @return
     */
    @GetMapping("/countMessagePublic")
    public int countMessagePublic() {
        int size = messagePublicMapper.selectList(null).size();
        return size;
    }
    /**
     * 未指派的信息记录条数
     */
    @GetMapping("/countUnGriddler")
    public int countUnGriddler() {
        int size = messagePublicMapper.selectList(Wrappers.<MessagePublic>lambdaQuery()
                .eq(MessagePublic::getStatus, 1)).size();
        return size;
    }
    /**
     * 等待指派的信息记录条数
     */
    @GetMapping("/countAlreadyAssigned")
    public int countAlreadyAssigned() {
        int size = messagePublicMapper.selectList(Wrappers.<MessagePublic>lambdaQuery()
                .eq(MessagePublic::getStatus, 0)).size();
        return size;
    }
}

