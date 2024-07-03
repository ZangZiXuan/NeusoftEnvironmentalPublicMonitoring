package com.example.springcloudmessagepublic.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springcloudapi.dao.entity.MessagePublic;

import java.util.Map;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/3 10:18
 * @Version 1.0
 */
public interface MessagePubilcService extends IService<MessagePublic> {
    public Map<String, Object> getPaginatedMessagePublics(int current, int size, QueryWrapper<MessagePublic> queryWrapper);
}
