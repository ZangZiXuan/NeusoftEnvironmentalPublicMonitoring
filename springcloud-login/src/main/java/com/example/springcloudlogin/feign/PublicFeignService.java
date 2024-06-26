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
 * @Date 2024/6/25 13:45
 * @Version 1.0
 */
@FeignClient(name = "springcloud-public",url = "localhost:8089")
public interface PublicFeignService {
    @PostMapping("/public/findPublic")
    Map<String,Object> findPublic(@RequestBody LoginDTO loginDTO);
}
