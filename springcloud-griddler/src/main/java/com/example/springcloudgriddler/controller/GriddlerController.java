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
import org.springframework.web.bind.annotation.*;

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

        HashMap<String, Object> response = new HashMap<>();
        List<Griddler> griddlers = griddlerMapper.selectList(Wrappers.<Griddler>lambdaQuery()
                .eq(Griddler::getCode, loginDTO.getUsername())
                .eq(Griddler::getPassword, MD5Util.encode(loginDTO.getPassword())));

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
    /**
     * 查看能够安排任务得所有的网格员
     * 找到能工作的网格员
     * @RequestParam: null
     * @Response: Map
     */

    @GetMapping ("/selectGriddlerStatus")
    public Map<String, Object> selectGriddlerStatus() {
        HashMap<String, Object> response = new HashMap<>();
        List<Griddler> griddlers = griddlerMapper.selectList(Wrappers.<Griddler>lambdaQuery()
                .eq(Griddler::getStatuses, 1));

        if(griddlers.isEmpty()) {
            response.put("message","登录者的身份不是网格员，或者如果他是网格员的话账户或者密码错误了");
            response.put("data",null);
            response.put("success",false);
            //返回 400 Bad Request 表示请求不合法.(待推敲哪个状态码更合适)
            return response;
        }else {
            response.put("data",griddlers);
            response.put("success", true);
            response.put("message", "查看所有可以使用的网格员（状态为0是正常工作的）");
//            System.out.println(response);
            return response;
        }
    }
    /**
     * 异地指派
     * 查看某个省某个市的网格员
     * 用于分配任务时要不要异地指派
     * @param offsiteStatus 是否异地指派
     * @param provinceId 省份id
     * @param cityId 城市id
     * @return Map
     */
    @GetMapping("/selectPlaceGriddler")
    public Map<String,Object> selectPlaceGriddler(@RequestParam("offsiteStatus") int offsiteStatus,
                                                  @RequestParam("provinceId") String provinceId,
                                                  @RequestParam("cityId") String cityId) {
        HashMap<String, Object> response = new HashMap<>();
        if(offsiteStatus != 0) {
            List<Griddler> griddlers = griddlerMapper.selectList(Wrappers.<Griddler>lambdaQuery()
                    .eq(Griddler::getStatuses, 0)
                    .eq(Griddler::getProvinceId, provinceId)
                    .eq(Griddler::getCityId, cityId));

            if(griddlers.isEmpty()) {
                response.put("message","没有符合条件的网格员");
                response.put("data",null);
                response.put("success",false);
                return response;
            }else {
                response.put("data",griddlers);
                response.put("success", true);
                response.put("message", "筛选异地指派的所有可用的网格员");
                return response;
            }
        }else  {
            response.put("data",null);
            response.put("success", false);
            response.put("message", "不是异地指派不应该调用当前接口");
            return response;
        }
    }


    @GetMapping("/findGriddlerName")
    public Griddler selectPlaceGriddler(@RequestParam("griddlerId") String griddlerId) {
        Griddler griddler = griddlerMapper.selectOne(Wrappers.<Griddler>lambdaQuery().eq(Griddler::getId, griddlerId));
        return griddler;
    }

    @GetMapping("/selectOneGriddlerAssign")
    public Map<String,Object> selectOneGriddlerAssign(@RequestParam("cityId") String cityId,
                                            @RequestParam("provinceId") String provinceId) {
        List<Griddler> griddlers = griddlerMapper.selectList(Wrappers.<Griddler>lambdaQuery()
                .eq(Griddler::getStatuses, 0)
                .eq(Griddler::getProvinceId, provinceId)
                .eq(Griddler::getCityId, cityId));
        HashMap<String, Object> response = new HashMap<>();
        if (griddlers.isEmpty()) {
            response.put("message","该地区无可用网格员");
            response.put("data",null);
            response.put("success",false);
            return response;
        }else {
            response.put("message","查询该地区所有的网格员成功");
            response.put("data",griddlers);
            response.put("success",true);
            return response;
        }
    }
}
