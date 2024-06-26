package com.example.springcloudlogin.feign;

import com.example.springcloudapi.dao.dto.LoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/25 11:39
 * @Version 1.0
 */
@FeignClient(name = "springcloud-manager",url = "localhost:8084")
public interface ManagerFeignService {
    @PostMapping("/manager/findManager")
    Map<String, Object> findManager(@RequestBody LoginDTO loginDTO);
}
