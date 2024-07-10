package com.example.springcloudmessagepublic.feign;

import com.example.springcloudapi.dao.dto.ViewPageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/3 13:56
 * @Version 1.0
 */
@FeignClient(name = "springcloud-messageManager",url = "localhost:8086")
public interface MessageManagerFegnService {
    @GetMapping("/messageManager/viewPageMessageManager")
    public Map<String, Object> viewPageMessageManager(@RequestParam("messageId") String messageId);

    @GetMapping("/messageManager/updateMessageStatus")
    public Map<String, Object> updateMessageStatus(@RequestParam("messageId") String messageId);

}
