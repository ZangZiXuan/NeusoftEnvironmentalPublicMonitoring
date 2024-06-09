package com.example.neps.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.neps.dao.entity.Message;
import com.example.neps.mapper.MessageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/7 9:29
 * @Version 1.0
 */

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageMapper messageMapper;

    @PostMapping ("/createMessage")
    public ResponseEntity<Map<String, Object>> creatMessage(@RequestBody Message message) throws ParseException {
        Map<String,Object> response = new HashMap<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        Date date = dateFormat.parse(formattedDate);

        String id = UUID.randomUUID().toString();
        Message message1 = new Message(id, message.getUserId(), message.getProvince(),
                message.getCity(), message.getLevel(),
                date, message.getDetail(),message.getAddress());
        int i = messageMapper.insert(message1);
        if(i == 0){
            response.put("success:",false);
            response.put("data:",null);
            response.put("message","提交空气质量监督信息失败");
            return ResponseEntity.ok(response);
        }else {
            response.put("success:",true);
            response.put("data:",message);
            response.put("message","提交空气质量监督信息成功");
            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping("/viewMyMessage/{userId}")
    public ResponseEntity<Map<String, Object>> viewMyMessage(@PathVariable String userId){
        Map<String,Object> response = new HashMap<>();
        List<Message> list = messageMapper.selectList(Wrappers.<Message>lambdaQuery().eq(Message::getUserId, userId));
        if(list.isEmpty()){
            response.put("success:",false);
            response.put("data:",null);
            response.put("message","暂无任何提交记录");
            return ResponseEntity.ok(response);
        }else {
            response.put("success:",true);
            response.put("data:",list);
            response.put("message","查询当前用户的所有提交记录成功");
            return ResponseEntity.ok(response);
        }
    }

}
