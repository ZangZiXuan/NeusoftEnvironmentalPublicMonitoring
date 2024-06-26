package com.example.springcloudmessagemanager.controller;

import com.example.springcloudapi.dao.entity.MessageManager;
import com.example.springcloudmessagemanager.mapper.MessageManagerMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/creatAssignedMessageManager/{messageId}")
    public ResponseEntity<Map<String,Object>> creatAssignedMessageManager(@PathVariable String messageId,
                                                                          @RequestBody MessageManager messageManager) {
        int insert = messageManagerMapper.insert(messageManager);
        Map<String, Object> response = new HashMap<>();

        if(insert == 1){
            response.put("success", true);
            response.put("message", "公众管理员端的提交添加成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "公众管理员端的提交添加失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
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
}
