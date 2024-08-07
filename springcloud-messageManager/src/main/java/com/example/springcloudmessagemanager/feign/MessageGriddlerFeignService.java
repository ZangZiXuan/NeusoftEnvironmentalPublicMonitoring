package com.example.springcloudmessagemanager.feign;

import com.example.springcloudapi.dao.entity.MessageGriddler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/1 13:59
 * @Version 1.0
 */
@FeignClient(name = "springcloud-messageGriddler",url = "localhost:8085")
public interface MessageGriddlerFeignService {
    @GetMapping("/messageGriddler/viewAllMessageGriddlerUndo")
    public Map<String,Object> viewMessageGriddlerUndo(@RequestParam("griddlerId") String griddler);

    @GetMapping("/messageGriddler/ViewGriddlerMessageDetail")
    public MessageGriddler ViewGriddlerMessageDetail(@RequestParam("messageId") String messageId);
}
