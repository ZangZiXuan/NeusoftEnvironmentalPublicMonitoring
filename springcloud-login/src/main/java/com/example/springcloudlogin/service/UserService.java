package com.example.springcloudlogin.service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 20:23
 * @Version 1.0
 */
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudapi.dao.entity.Manager;
import com.example.springcloudapi.dao.entity.PolicyMaker;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudgriddler.mapper.GriddlerMapper;
import com.example.springcloudmanager.mapper.ManagerMapper;
import com.example.springcloudpolicymaker.mapper.PolicyMakerMapper;
import com.example.springcloudpublic.mapper.PublicMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public Object authenticateUser(String username, String password) {
//        System.out.println("authenticateUser");
//
//        QueryWrapper<Griddler> griddlerQuery = new QueryWrapper<>();
//        griddlerQuery.eq("phone", username).eq("password", password);
//        Griddler griddler = griddlerMapper.selectOne(griddlerQuery);
//        if (griddler != null) {
//            return new AuthResult("griddler", griddler);
//        }
//
//        QueryWrapper<Manager> managerQuery = new QueryWrapper<>();
//        managerQuery.eq("manager_code", username).eq("password", password);
//        Manager manager = managerMapper.selectOne(managerQuery);
//        if (manager != null) {
//            return new AuthResult("manager", manager);
//        }


//        QueryWrapper<Public> publicQuery = new QueryWrapper<>();
//        publicQuery.eq("phone", username).eq("password", password);
//        Public publicUser = publicMapper.selectOne(publicQuery);
//        if (publicUser != null) {
//            return new AuthResult("public", publicUser);
//        }
        return null; // 用户未找到或密码不匹配
    }


}

