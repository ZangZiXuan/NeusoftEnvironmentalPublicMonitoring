package com.example.springcloudlogin.feign.hystrix;

import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudlogin.feign.GriddlerFeignService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/4 10:07
 * @Version 1.0
 */
@Component
public class GriddlerFall implements GriddlerFeignService {
    //熔断降级方法
    @Override
    public Map<String, Object> findGriddler(LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("message","fegin触发了熔断降级方法");
        response.put("data",null);
        response.put("code",403);
        //403：发送的请求被服务器拒绝
        return response;
    }


}
