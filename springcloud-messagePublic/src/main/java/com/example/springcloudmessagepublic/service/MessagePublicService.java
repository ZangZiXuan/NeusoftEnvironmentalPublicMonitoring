package com.example.springcloudmessagepublic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springcloudapi.dao.dto.MessagePublic;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 10:01
 * @Version 1.0
 */
@Service
public interface MessagePublicService extends IService<MessagePublic> {
    /**
     * 公众监督员提交message
     */
    public int addMessagePublic(MessagePublic messagePublic);
}
