package com.example.springcloudmessagemanager.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/26 14:37
 * @Version 1.0
 */
@FeignClient(name = "springcloud-griddler",url = "localhost:8082")
public interface GriddlerFeignService {
}
