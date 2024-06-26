package com.example.springcloudmessagepublic.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.dto.MessagePublicDTO;
import com.example.springcloudapi.dao.entity.City;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.dao.entity.Public;
//import com.example.springcloudapi.mapper.PublicMapper;
//import com.example.springcloudapi.service.PublicService;
import com.example.springcloudapi.mapper.CityMapper;
import com.example.springcloudapi.mapper.ProvinceMapper;
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
    ProvinceMapper provinceMapper;
    CityMapper cityMapper;
    PublicMapper publicMapper;
    /**
     * 公众监督员端的提交
     * @param messagePublic
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/submitMessagePublic")
    public ResponseEntity<Map<String, Object>> submitMessagePublic(@RequestBody MessagePublic messagePublic) {
        Map<String, Object> response = new HashMap<>();
        int insert = messagePublicMapper.insert(messagePublic);
        if(insert == 1){
            response.put("success", true);
            response.put("message", "公众监督员端的提交添加成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "公众监督员端的提交添加失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 查找特定id的本人提交的反馈信息
     * @param publicId
     * @return ResponseEntity
     */

    @RequestMapping("/{publicId}")
    public ResponseEntity<Map<String,Object>> ViewMyMessagePublic(@PathVariable String publicId) {
        Map<String, Object> response = new HashMap<>();

        List<MessagePublic> messagePublicList = messagePublicMapper.selectList
                (Wrappers.<MessagePublic>lambdaQuery().eq(MessagePublic::getPublicId, publicId));
        List<MessagePublicDTO> messagePublicDTOList = new ArrayList<>();
        if(!messagePublicList.isEmpty()){
            for (MessagePublic messagePublic :
                    messagePublicList) {
                City city = cityMapper.selectById(messagePublic.getCityId());
                Province province = provinceMapper.selectById(city.getProvinceId());

                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                  null,messagePublic,province.getProvinceName(),city.getCityName(), province.getShortTitle());
                messagePublicDTOList.add(messagePublicDTO);
            }
            response.put("success", true);
            response.put("message", "查询当前公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 查看所有的用户的所有反馈信息
     * @return ResponseEntity
     */
    @RequestMapping("/viewAllMessagePublic")
    public ResponseEntity<Map<String,Object>> viewAllMessagePublic(){
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
                Public publicDetail = publicMapper.selectById(publicId);

                City city = cityMapper.selectById(messagePublic.getCityId());
                Province province = provinceMapper.selectById(city.getProvinceId());
                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                        publicDetail,messagePublic, province.getProvinceName(), city.getCityName(), province.getShortTitle());
                messagePublicDTOList.add(messagePublicDTO);
            }
            response.put("success", true);
            response.put("message", "查询所有的公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "当前用户还未提交过反馈信息");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
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
    public ResponseEntity<Map<String,Object>> viewSomeMessagePublic(@RequestParam("provinceId") String provinceId,
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
                City city = cityMapper.selectById(messagePublic.getCityId());
                Province province = provinceMapper.selectById(city.getProvinceId());

                String publicId = messagePublic.getPublicId();
                /**
                 * 从查询public的具体信息
                 */
                Public publicDetail = publicMapper.selectById(publicId);

                MessagePublicDTO messagePublicDTO = new MessagePublicDTO(
                    publicDetail,messagePublic, province.getProvinceName(), city.getCityName(), province.getShortTitle());
                messagePublicDTOList.add(messagePublicDTO);
            }
            response.put("success", true);
            response.put("message", "用特定条件查询特定的公众监督员的提交记录成功");
            response.put("data",messagePublicDTOList);
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "没有符合筛选条件的公众监督员的提交记录");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }


}