package com.example.springcloudgriddler.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.Dept;
import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudapi.utils.MD5Util;
import com.example.springcloudgriddler.mapper.GriddlerMapper;
import com.example.springcloudgriddler.service.GriddlerService;
import com.example.springcloudgriddler.service.impl.GriddlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/24 19:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/griddler")
public class GriddlerController {

    @Autowired
    GriddlerMapper griddlerMapper;
    @PostMapping("/findGriddler")
    public Map<String, Object> findGriddler(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO);
        HashMap<String, Object> response = new HashMap<>();
        List<Griddler> griddlers = griddlerMapper.selectList(Wrappers.<Griddler>lambdaQuery()
                .eq(Griddler::getCode, loginDTO.getUsername())
                .eq(Griddler::getPassword, loginDTO.getPassword()));
        System.out.println(griddlers);
        if(griddlers.isEmpty()) {
            response.put("message","登录者的身份不是网格员，或者如果他是网格员的话账户或者密码错误了");
            response.put("data",null);
            response.put("success",false);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }else {
            response.put("data",griddlers.get(0));
            response.put("success", true);
            response.put("message", "登录者的身份是网格员");
//            System.out.println(response);
            return response;
        }

    }

}
