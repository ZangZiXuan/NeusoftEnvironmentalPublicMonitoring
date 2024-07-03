package com.example.springcloudmessagemanager.feign;

import com.example.springcloudapi.dao.entity.Griddler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 14:37
 * @Version 1.0
 */
@FeignClient(name = "springcloud-griddler",url = "localhost:8082")
public interface GriddlerFeignService {
    @GetMapping("/griddler/findGriddlerName")
    public Griddler selectPlaceGriddler(@RequestParam("griddlerId") String griddlerId);
}
