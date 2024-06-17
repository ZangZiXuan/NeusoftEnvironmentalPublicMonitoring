package com.example.springcloudpublic.controller;

import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudapi.mapper.PublicMapper;
import com.example.springcloudapi.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 15:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    PublicMapper publicMapper;

    /**
     * 注册公众监督员成功
     * @param public1
     * @return ResponseEntity
     */

    @PostMapping("/addPublic1")
    public ResponseEntity<Map<String,Object>> addPublic(@RequestBody Public public1){
        public1.setId(UUID.randomUUID().toString());
        public1.setPassword(MD5Util.encode(public1.getPassword()));
        int insert = publicMapper.insert(public1);

        Map<String, Object> response = new HashMap<>();

        if(insert == 1){
            response.put("success", true);
            response.put("message", "注册公众监督员成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "注册公众监督员失败");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);
        }
    }
}
