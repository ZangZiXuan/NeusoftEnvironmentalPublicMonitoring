package com.example.springcloudmessagegriddler.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springcloudapi.dao.entity.MessagePublic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 16:02
 * @Version 1.0
 */
@FeignClient(name = "springcloud-messagePublic",url = "localhost:8087")
public interface MessagePublicFeignService {
    @PostMapping("/messagePublic/selectMessagePublic")
    public Map<String,Object> selectMessagePublic(@RequestParam("messageId") String messageId);

    @GetMapping("/messagePublic/findAllAddressByCityId")
    public List<String> findAllAddressByCity();

    /**
     * 全部的信息记录条数
     * @return
     */
    @GetMapping("/messagePublic/countMessagePublic")
    public int countMessagePublic() ;
    /**
     * 未指派的信息记录条数
     */
    @GetMapping("/messagePublic/countUnGriddler")
    public int countUnGriddler() ;
    /**
     * 等待指派的信息记录条数
     */
    @GetMapping("/messagePublic/countAlreadyAssigned")
    public int countAlreadyAssigned();
}
