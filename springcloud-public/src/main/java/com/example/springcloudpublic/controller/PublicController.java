package com.example.springcloudpublic.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudpublic.mapper.PublicMapper;
import com.example.springcloudapi.utils.MD5Util;
import com.example.springcloudpublic.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
     * 公众监督员登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/findPublic")
    public Map<String,Object> findPublic(@RequestBody LoginDTO loginDTO) {
        List<Public> publics = publicMapper.selectList(Wrappers.<Public>lambdaQuery()
                .eq(Public::getPassword, loginDTO.getPassword())
                .eq(Public::getPhone, MD5Util.encode(loginDTO.getUsername())));
        System.out.println(publics);
        HashMap<String, Object> response = new HashMap<>();
        if(publics.isEmpty()) {
            response.put("message","登录者的身份不是公众监督员，或者如果他是公众监督员的话账户或者密码错误了");
            response.put("success",false);
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }else {
            response.put("data",publics.get(0));
            response.put("success", true);
            response.put("message", "登录者的身份是管理员");
            return response;
        }

    }

    /**
     * 注册公众监督员成功
     * @param public1
     * @return ResponseEntity
     */

    @PostMapping("/addPublic")
    public ResponseEntity<Map<String,Object>> addPublic(@RequestBody Public public1){
        System.out.println("=-------------------");
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
    @GetMapping ("/publicDetail")
    public Public getPublicById(@RequestBody String publicId) {
        Public aPublic = publicMapper.selectOne(Wrappers.<Public>lambdaQuery()
                .eq(Public::getId, publicId));
        System.out.println(aPublic);
        return aPublic;
    }

}
