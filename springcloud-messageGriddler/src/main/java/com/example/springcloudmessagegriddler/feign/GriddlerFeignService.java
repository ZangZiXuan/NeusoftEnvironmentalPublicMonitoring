package com.example.springcloudmessagegriddler.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/7 9:09
 * @Version 1.0
 */
@FeignClient(name = "springcloud-griddler",url = "localhost:8082")
public interface GriddlerFeignService {
    @GetMapping("/griddler/selectGriddlerName")
    public String selectGriddlerName(@RequestParam("griddlerId") String griddlerId);
    @GetMapping("/griddler/selectGriddlerPhone")
    public String selectGriddlerPhone(@RequestParam("griddlerId") String griddlerId);
}
