package com.example.springcloudmessagemanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.MessagePublicDTO;
import com.example.springcloudapi.dao.entity.MessageManager;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudmessagemanager.dto.ShowDetailDTO;
import com.example.springcloudmessagemanager.feign.CitiesFeignService;
import com.example.springcloudmessagemanager.feign.MessageGriddlerFeignService;
import com.example.springcloudmessagemanager.feign.MessagePublicFeignService;
import com.example.springcloudmessagemanager.mapper.MessageManagerMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 11:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/messageManager")
public class MessageManagerController {

    @Autowired
    MessageManagerMapper messageManagerMapper;

    /**
     * 管理员分配任务给网格员
     * @param messageManager
     * @return Map
     */

    @PostMapping("/creatAssignedMessageManager")
    public Map<String,Object> creatAssignedMessageManager(@RequestBody MessageManager messageManager) {

        int insert = messageManagerMapper.insert(messageManager);
        Map<String, Object> response = new HashMap<>();

        if(insert == 1){
            response.put("success", true);
            response.put("message", "公众管理员端的提交添加成功");
            response.put("data",insert);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "公众管理员端的提交添加失败");
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }

    @RequestMapping("/viewAllAssignedMessageManager")
    public ResponseEntity<Map<String,Object>> viewAllAssignedMessageManager(@RequestParam("status") Integer status) {
        List<MessageManager> messageManagers = messageManagerMapper.selectList(null);

        Map<String, Object> response = new HashMap<>();

        if(!messageManagers.isEmpty()){
            response.put("success", true);
            response.put("message", "查看所有的公众管理员端的提交成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "查看所有的公众管理员端的提交失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }
    @Autowired
    MessagePublicFeignService messagePublicFeignService;
    @Autowired
    CitiesFeignService citiesFeignService;
    @Autowired
    MessageGriddlerFeignService messageGriddlerFeignService;
    @GetMapping("/viewOneGriddlerAssigned/{griddlerId}")
    public Map<String,Object> viewOneGriddlerAssigned(@PathVariable("griddlerId") String griddlerId) {
        List<MessageManager> messageManagerList = messageManagerMapper.selectList(Wrappers.
                <MessageManager>lambdaQuery()
                .eq(MessageManager::getGriddlerId, griddlerId)
                        .eq(MessageManager::getStatus,0)
                );

        List<ShowDetailDTO> list = new ArrayList<>();
        HashMap<String, Object> response = new HashMap<>();
        for(MessageManager messageManager:messageManagerList) {

            Object response1 = messagePublicFeignService.selectMessagePublic(messageManager.getMessageId());
            // 获取data部分
            Map<String, Object> responseMap = (Map<String, Object>) response1;
            Object data = responseMap.get("data");


            // 检查data是否为空
            if (data != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                MessagePublic messagePublic = objectMapper.convertValue(data, MessagePublic.class);

                String provinceName = citiesFeignService.selectProvinceName(messagePublic.getProvinceId());
                String cityName = citiesFeignService.selectCityName(messagePublic.getCityId());
                int level = messagePublic.getLevel();
                String address = messagePublic.getAddress();
                Date date = messagePublic.getDate();
                ShowDetailDTO showDetailDTO = new ShowDetailDTO(provinceName, cityName, level, address, date);

                list.add(showDetailDTO);
            }
        }
        if(!list.isEmpty()) {
            response.put("success", true);
            response.put("message", "查看待做任务成功");
            response.put("data",list);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "查看待做任务失败");
            response.put("data",null);
            return response;
        }
    }

}
