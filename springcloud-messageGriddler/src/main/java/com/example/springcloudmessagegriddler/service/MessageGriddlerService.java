package com.example.springcloudmessagegriddler.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springcloudapi.dao.entity.Manager;
import com.example.springcloudapi.dao.entity.MessageGriddler;
import com.example.springcloudapi.dao.entity.MessagePublic;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/7 9:24
 * @Version 1.0
 */
public interface MessageGriddlerService extends IService<MessageGriddler> {

    public Map<String,Object> getPaginatedMessageGriddlers(int current,int size, QueryWrapper<MessageGriddler> queryWrapper);
}
