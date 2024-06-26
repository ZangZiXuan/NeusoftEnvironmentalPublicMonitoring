package com.example.springcloudpublic.API;

import com.example.springcloudapi.dao.entity.Public;

import com.example.springcloudpublic.mapper.PublicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/18 19:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicAPIImpl {
    private final PublicMapper publicMapper;

    @PostMapping("/selectById")
    public Public selectPublicById(@RequestBody String id){
        return publicMapper.selectById(id);
    }

}
