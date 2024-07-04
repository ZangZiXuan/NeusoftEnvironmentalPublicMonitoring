package com.example.springcloudmessagemanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.MessagePublicDTO;
import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudapi.dao.entity.MessageGriddler;
import com.example.springcloudapi.dao.entity.MessageManager;
import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudmessagemanager.dto.ShowDetailDTO;
import com.example.springcloudmessagemanager.dto.ViewPageDTO;
import com.example.springcloudmessagemanager.feign.CitiesFeignService;
import com.example.springcloudmessagemanager.feign.GriddlerFeignService;
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
        messagePublicFeignService.updateMessagePublic(messageManager.getMessageId());
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

            Object data = messagePublicFeignService.selectMessagePublic(messageManager.getMessageId()).get("data");

            System.out.println(data);


            // 检查data是否为空
            if (data != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                MessagePublicDTO messagePublic = objectMapper.convertValue(data, MessagePublicDTO.class);

                String provinceName = citiesFeignService.selectProvinceName(messagePublic.getMessagePublic().getProvinceId());
                String cityName = citiesFeignService.selectCityName(messagePublic.getMessagePublic().getCityId());
                int level = messagePublic.getMessagePublic().getLevel();
                String address = messagePublic.getMessagePublic().getAddress();
                Date date = messagePublic.getMessagePublic().getDate();
                ShowDetailDTO showDetailDTO = new ShowDetailDTO(provinceName, cityName, level, address, date,messagePublic.getMessagePublic().getId());

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

    @GetMapping("/updateMessageStatus")
    public Map<String, Object> updateMessageStatus(@RequestParam("messageId") String messageId) {
        // 查询要更新的记录
        MessageManager messageManager = messageManagerMapper.selectOne(
                Wrappers.<MessageManager>lambdaQuery()
                        .eq(MessageManager::getMessageId, messageId)
        );

        if (messageManager != null) {
            // 更新status字段为1
            messageManager.setStatus(1);

            // 执行更新操作
            int update = messageManagerMapper.updateById(messageManager);

            // 构建响应
            Map<String, Object> response = new HashMap<>();
            if (update == 1) {
                response.put("success", true);
                response.put("message", "更新成功");
            } else {
                response.put("success", false);
                response.put("message", "更新失败");
            }
            return response;
        } else {
            // 如果没有找到对应的记录
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "未找到对应的记录");
            return response;
        }
    }
    @Autowired
    GriddlerFeignService griddlerFeignService;
    @GetMapping("/viewPageMessageManager")
    public Map<String,Object> viewPageMessageManager(@RequestParam("messageId") String messageId) {

        Map<String, Object> response = new HashMap<>();
        //查看
        MessageManager messageManager = messageManagerMapper.selectOne(
                Wrappers.<MessageManager>lambdaQuery()
                        .eq(MessageManager::getMessageId, messageId)

        );
        String s = "";
        if(messageManager!=null && messageManager.getStatus()==0) {
            s += "已指派，等待网格员确认";

            Griddler griddler = griddlerFeignService.selectPlaceGriddler(messageManager.getGriddlerId());
            response.put("data",new ViewPageDTO(messageManager,griddler.getName(),s));
            response.put("message","当前信息已指派，等待网格员确认");

        } else if(messageManager!=null && messageManager.getStatus()==1){
            s += "已确认";
            MessageGriddler messageGriddler = messageGriddlerFeignService.ViewGriddlerMessageDetail(messageManager.getMessageId());
            Griddler griddler = griddlerFeignService.selectPlaceGriddler(messageManager.getGriddlerId());
            ViewPageDTO viewPageDTO = new ViewPageDTO(messageManager, messageGriddler, griddler.getName(), s);
            response.put("data",viewPageDTO);
            response.put("message","当前信息已由网格员确认");
            response.put("success",true);

        }else {
            response.put("data",null);
            response.put("success",false);

        }
        return response;
    }
}
