package com.example.neps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.neps.dao.entity.Message;
import com.example.neps.mapper.MessageMapper;
import com.example.neps.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/7 9:27
 * @Version 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {
}
