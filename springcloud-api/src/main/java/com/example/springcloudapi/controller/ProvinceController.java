package com.example.springcloudapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 15:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    ProvinceMapper provinceMapper;

    @PostMapping("/selectProvince")
    public Map<String,Object> selectProvince(@RequestBody String provinceId) {
        Province province = provinceMapper.selectOne(Wrappers.<Province>lambdaQuery().eq(Province::getId, provinceId));
        HashMap<String, Object> response = new HashMap<>();
        if(province != null) {
            response.put("message","找到对应的省简称成功");
            response.put("data",province);
            response.put("success",true);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        } else {
            response.put("message","找到对应的省简称失败");
            response.put("data",null);
            response.put("success",false);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }
    }
    @GetMapping("/selectprovinceName")
    public String selectProvinceName(@RequestParam("provinceId") String provinceId) {
        String provinceName = provinceMapper.selectOne(Wrappers.<Province>lambdaQuery().eq(Province::getProvinceId, provinceId)).getProvinceName();
        return provinceName;
    }

    @GetMapping("/selectAllProvince")
    public List<String> selectProvince() {
        List<Province> provinces = provinceMapper.selectList(null);
        ArrayList<String> list = new ArrayList<>();
        for(Province province:provinces) {
            list.add(province.getProvinceName());
        }
        return list;
    }
}
