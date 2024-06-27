package com.example.springcloudapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.dto.PlaceDTO;
import com.example.springcloudapi.dao.entity.City;
import com.example.springcloudapi.dao.entity.Province;
import com.example.springcloudapi.mapper.CityMapper;
import com.example.springcloudapi.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 15:14
 * @Version 1.0
 */
@RestController
public class CitiesController {
    @Autowired
    CityMapper cityMapper;
    @Autowired
    ProvinceMapper provinceMapper;

    @RequestMapping("/selectCity")
    public Map<String,Object> selectCity(@RequestParam("cityId") String cityId) {
        City city = cityMapper.selectOne(Wrappers.<City>lambdaQuery()
                .eq(City::getId, cityId));
        Province province = provinceMapper.selectOne(Wrappers.<Province>lambdaQuery()
                .eq(Province::getId, city.getProvinceId()));
        HashMap<String, Object> response = new HashMap<>();
        PlaceDTO placeDTO = new PlaceDTO(province.getShortTitle(), province.getId(), cityId);
        if (city.getProvinceId() != null && province.getShortTitle() != null) {
            response.put("success", true);
            response.put("message", "查询该市的其他地址信息成功");
            response.put("data",placeDTO);
            return response;
        }else {
            response.put("success", false);
            response.put("message", "查询该市的其他地址信息失败");
            response.put("data",null);
            return response;
        }

    }
}