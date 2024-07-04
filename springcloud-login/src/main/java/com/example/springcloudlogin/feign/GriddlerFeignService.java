package com.example.springcloudlogin.feign;

import com.example.springcloudapi.dao.dto.LoginDTO;
import com.example.springcloudlogin.feign.hystrix.GriddlerFall;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/24 21:02
 * @Version 1.0
 */


@FeignClient(name = "springcloud-griddler",url = "localhost:8082",fallback = GriddlerFall.class )
public interface GriddlerFeignService {
    //声明需要调用的rest接口对应的方法
    @PostMapping("/griddler/findGriddler")
    Map<String, Object> findGriddler(@RequestBody LoginDTO loginDTO);
}
