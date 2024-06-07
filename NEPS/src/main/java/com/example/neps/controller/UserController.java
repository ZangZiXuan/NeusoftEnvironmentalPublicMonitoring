package com.example.neps.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.neps.dao.entity.User;
import com.example.neps.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/6 22:50
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/creatUser")
    public Map<String,Object> creatUser(@RequestParam("phone") String phone,
                                        @RequestParam("age") Integer age,
                                        @RequestParam("sex") Integer sex,
                                        @RequestParam("password") String password){

        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone));
        Map<String,Object> response = new HashMap<>();
        if(user1 == null){
            String id = UUID.randomUUID().toString();
            User user = new User(id, phone, age, sex, password);
            userMapper.insert(user);
            response.put("success:",true);
            response.put("data:",user);
            response.put("message","注册成功");
        }else{
            response.put("success:",false);
            response.put("data:",null);
            response.put("message","该号码已经注册");
        }
        return response;
    }


    @GetMapping("/loginUser")
    public Map<String,Object> loginChild(@RequestParam("phone") String phone,
                                         @RequestParam("password") String password){
        Map<String,Object> response = new HashMap<>();
        //将输入的数据与数据库进行匹配，看是否存在
        User hasUser = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone)
                .eq(User::getPassword, password));
        if (hasUser == null) {
            response.put("success:",false);
            response.put("data:",null);
            response.put("message","登录失败，用户名与密码不匹配");
        } else {
            response.put("success:",true);
            response.put("data:",hasUser);
            response.put("message","登录成功");
        }
        return response;
    }
}
