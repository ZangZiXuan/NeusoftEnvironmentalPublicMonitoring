package com.example.springcloudlogin.controller;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 20:31
 * @Version 1.0
 */

import com.example.springcloudapi.dao.dto.Dept;
import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudapi.dao.entity.Manager;
import com.example.springcloudapi.dao.entity.PolicyMaker;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudlogin.feign.GriddlerFeignService;
import com.example.springcloudlogin.feign.ManagerFeignService;
import com.example.springcloudlogin.feign.PolicyMakerFeignService;
import com.example.springcloudlogin.feign.PublicFeignService;
import com.example.springcloudlogin.service.UserService;
import com.example.springcloudapi.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    GriddlerFeignService griddlerFeignService;
    @Autowired
    ManagerFeignService managerFeignService;
    @Autowired
    PublicFeignService publicFeignService;
    @Autowired
    PolicyMakerFeignService policyMakerFeignService;
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginDTO loginDTO) {
        HashMap<String, Object> response = new HashMap<>();

        Map<String, Object> griddler = griddlerFeignService.findGriddler(loginDTO);
        System.out.println(griddler);
        Dept dept = new Dept();

        if(griddler.get("data") != null) {
            System.out.println(griddler.get("data"));
            Object data = griddler.get("data");
            dept.setDname("griddler");
            dept.setUser(data);
        }

        Map<String, Object> manager = managerFeignService.findManager(loginDTO);

        if(manager.get("data")!= null) {
            Object data = manager.get("data");
            dept.setDname("manager");
            dept.setUser(data);
        }
        Map<String, Object> policyMaker = policyMakerFeignService.findPolicyMaker(loginDTO);

        if(policyMaker.get("data") != null){
            Object data = policyMaker.get("data");
            dept.setDname("policyMaker");
            dept.setUser(data);
        }
        Map<String, Object> aPublic = publicFeignService.findPublic(loginDTO);
        if(aPublic.get("data") != null) {
            Object data = aPublic.get("data");
            dept.setDname("public");
            dept.setUser(data);
        }

        if(dept.getDname()== null) {
            response.put("message","账号或密码错误");
            response.put("success",false);
            response.put("data",null);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }else {
            response.put("data",dept);
            response.put("success", true);
            response.put("message", "登录成功");
            return response;
        }
    }

}
