package com.example.springcloudpolicymaker.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudapi.dao.entity.PolicyMaker;
import com.example.springcloudpolicymaker.mapper.PolicyMakerMapper;
import com.example.springcloudpolicymaker.service.impl.PolicyMakerServiceImpl;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/21 10:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/policyMaker")
public class PolicyMakerController {
    @Autowired
    PolicyMakerMapper policyMakerMapper;
    @PostMapping("/findPolicyMaker")
    public Map<String,Object> findPolicyMaker(@RequestBody LoginDTO loginDTO) {
        HashMap<String, Object> response = new HashMap<>();

        List<PolicyMaker> policyMakers = policyMakerMapper.selectList(Wrappers.<PolicyMaker>lambdaQuery()
                .eq(PolicyMaker::getUsername, loginDTO.getUsername())
                .eq(PolicyMaker::getPassword, loginDTO.getPassword()));
        if (!policyMakers.isEmpty()) {
//            PolicyMaker policyMaker = policyMakers.get(0);
            response.put("status", "success");
            response.put("data", policyMakers.get(0));
            return response;
        } else {
            response.put("status", "error");
            response.put("data",null);
            response.put("message", "Invalid username or password");
            return response;
        }
    }
}
