package com.example.nepg.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.nepg.dao.entity.User;
import com.example.nepg.mapper.UserMapper;
import com.example.nepg.utils.MD5Util;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/9 15:02
 * 网格员端
 * @Version 1.0
 */
@RestController
@RequestMapping("/mesh-worker")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/loginMeshWorker")
    public ResponseEntity<Map<String, Object>> loginMeshWorker(@RequestParam("username") String userName,
                                                             @RequestParam("password") String password){
        Map<String,Object> response = new HashMap<>();
        //将输入的数据与数据库进行匹配，看是否存在
        User hasUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, userName)
                .eq(User::getPassword, MD5Util.encode(password)));
        if (hasUser == null) {
            response.put("success",false);
            response.put("data",null);
            response.put("message","登录失败，用户名与密码不匹配");
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return ResponseEntity.badRequest().body(response);

        } else {
            response.put("success",true);
            response.put("data",hasUser);
            response.put("message","登录成功");
            return ResponseEntity.ok(response);
        }

    }
}
