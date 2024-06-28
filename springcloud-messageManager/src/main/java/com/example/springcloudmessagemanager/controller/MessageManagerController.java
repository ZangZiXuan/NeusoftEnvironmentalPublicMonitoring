package com.example.springcloudmessagemanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.MessageManager;
import com.example.springcloudmessagemanager.mapper.MessageManagerMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
//    @RequestMapping("/viewOneGriddlerAssigned/{griddlerId}")
//    public Map<String,Object> viewOneGriddlerAssigned(@PathVariable("griddlerId") String griddlerId) {
//        List<MessageManager> messageManagerList = messageManagerMapper.selectList(Wrappers.<MessageManager>lambdaQuery()
//                .eq(MessageManager::getGriddlerId, griddlerId));
//        List<String> griddlerIdList = new ArrayList<>();
//        for(int i = 0; i < messageManagerList.size(); i++) {
//            griddlerIdList.add(messageManagerList.get(i).getGriddlerId());
//
//        }
//
////                MessageManager::getGriddlerId,griddlerId
//    }
}
