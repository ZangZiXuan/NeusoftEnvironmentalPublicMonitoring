package com.example.springcloudmessagepublic.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.dto.MessagePublicDTO;
import com.example.springcloudapi.dao.entity.City;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudapi.mapper.CityMapper;
import com.example.springcloudapi.mapper.ProvinceMapper;
import com.example.springcloudmessagepublic.feign.CitiesFeignService;
import com.example.springcloudmessagepublic.feign.ProvinceFeignService;
import com.example.springcloudmessagepublic.feign.PublicFeignService;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    ProvinceFeignService provinceFeignService;

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
    public Map<String,Object> ViewMyMessagePublic(@PathVariable String publicId) {
        Map<String, Object> response = new HashMap<>();

        List<MessagePublic> messagePublicList = messagePublicMapper.selectList
                (Wrappers.<MessagePublic>lambdaQuery().eq(MessagePublic::getPublicId, publicId));
        List<MessagePublicDTO> messagePublicDTOList = new ArrayList<>();
        if(!messagePublicList.isEmpty()){
            for (MessagePublic messagePublic :
                    messagePublicList) {
                City city = (City)citiesFeignService.selectCity(messagePublic.getCityId());
                Province province = (Province) provinceFeignService.selectProvince(city.getProvinceId());

                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                  null,messagePublic,province.getProvinceName(),city.getCityName(), province.getShortTitle());
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
    @RequestMapping("/viewAllMessagePublic")
    public Map<String,Object> viewAllMessagePublic(){
        HashMap<String, Object> response = new HashMap<>();
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
//City city = (City)citiesFeignService.selectCity(messagePublic.getCityId());
//                Province province = (Province) provinceFeignService.selectProvince(city.getProvinceId());
                City city = (City)citiesFeignService.selectCity(messagePublic.getCityId());
                Province province = (Province) provinceFeignService.selectProvince(city.getProvinceId());
                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                        publicDetail,messagePublic, province.getProvinceName(), city.getCityName(), province.getShortTitle());
                messagePublicDTOList.add(messagePublicDTO);
            }
            response.put("success", true);
            response.put("message", "查询所有的公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }

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
                Province province = (Province) provinceFeignService.selectProvince(city.getProvinceId());

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
     * @return
     */
    @RequestMapping("/selectMessagePublic")
    public Map<String,Object> selectMessagePublic(@RequestParam("publicId") String pubicId) {
        HashMap<String, Object> response = new HashMap<>();
        List<MessagePublic> messagePublics = messagePublicMapper.selectList(Wrappers.<MessagePublic>lambdaQuery().eq(MessagePublic::getPublicId, pubicId));

        if(messagePublics.isEmpty()) {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;

        }else {
            response.put("success", true);
            response.put("message", "查询所有的公众监督员的提交记录成功");
            response.put("data",messagePublics);
            return response;
        }
    }
}

