package com.example.springcloudmanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudapi.dao.entity.Manager;
import com.example.springcloudapi.utils.MD5Util;
import com.example.springcloudmanager.mapper.ManagerMapper;
import com.example.springcloudmanager.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/25 11:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerMapper managerMapper;

    @PostMapping("/findManager")
    public Map<String, Object> findManager(@RequestBody LoginDTO loginDTO) {
        List<Manager> managers = managerMapper.selectList(Wrappers.<Manager>lambdaQuery().eq(Manager::getManagerCode, loginDTO.getUsername())
                .eq(Manager::getPassword, MD5Util.encode(loginDTO.getPassword())));
        HashMap<String, Object> response = new HashMap<>();
        if(managers.isEmpty()) {
            response.put("message","登录者的身份不是管理员，或者如果他是管理员的话账户或者密码错误了");
            response.put("data",null);
            response.put("success",false);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }else {
            response.put("data",managers.get(0));
            response.put("success", true);
            response.put("message", "登录者的身份是管理员");
            return response;
        }

    }
}
