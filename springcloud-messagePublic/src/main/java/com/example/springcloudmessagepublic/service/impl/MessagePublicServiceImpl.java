package com.example.springcloudmessagepublic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.dto.MessagePublic;
import com.example.springcloudmessagepublic.mapper.MessagePublicMapper;
import com.example.springcloudmessagepublic.service.MessagePublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 10:01
 * @Version 1.0
 */
@Service

public class MessagePublicServiceImpl extends ServiceImpl<MessagePublicMapper, MessagePublic> implements MessagePublicService {

    @Autowired
    MessagePublicMapper messagePublicMapper;

    @Override
    public int addMessagePublic(MessagePublic messagePublic) {
        //返回值通常是插入操作的影响行数，通常是一个整数，表示成功插入的记录数。
        //如果插入成功，它会返回 1，表示一行记录已插入。如果插入失败则返回 0
        //purchase.setStatus(false);
        int result1 = messagePublicMapper.insert(messagePublic);
        return result1;
    }
}
