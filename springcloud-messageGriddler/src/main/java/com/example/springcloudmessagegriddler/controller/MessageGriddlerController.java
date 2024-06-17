package com.example.springcloudmessagegriddler.controller;

import com.example.springcloudapi.dao.dto.MessageGriddler;
import com.example.springcloudapi.mapper.GriddlerMapper;
import com.example.springcloudapi.utils.CommUtil;
import com.example.springcloudmessagegriddler.mapper.MessageGriddlerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @PostMapping("/creatMessageGriddler")
    public ResponseEntity<Map<String,Object>> creatMessageGriddler(@RequestBody MessageGriddler messageGriddler) {
        messageGriddler.setTime(CommUtil.getNowDateLongStr("yyyy-mm-dd HH:mm:ss"));
        messageGriddler.setId(UUID.randomUUID().toString());
        messageGriddler.setStatus(1);
        int insert = messageGriddlerMapper.insert(messageGriddler);
        Map<String, Object> response = new HashMap<>();

        if(insert == 1){
            response.put("success", true);
            response.put("message", "网格员端的提交实测数据添加成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "网格员端的提交实测数据添加失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/viewAllMessageGriddler")
    public ResponseEntity<Map<String,Object>> viewAllMessageGriddler() {
        List<MessageGriddler> messageGriddlerList = messageGriddlerMapper.selectList(null);

        Map<String, Object> response = new HashMap<>();

        if(!messageGriddlerList.isEmpty()){
            response.put("success", true);
            response.put("message", "查看所有的网格员端的提交实测数据成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "查看所有的网格员端的提交实测数据失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }

}
