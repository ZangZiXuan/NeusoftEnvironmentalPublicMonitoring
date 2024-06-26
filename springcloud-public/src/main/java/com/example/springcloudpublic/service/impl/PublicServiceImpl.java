package com.example.springcloudpublic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudpublic.mapper.PublicMapper;
import com.example.springcloudpublic.service.PublicService;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/19 14:16
 * @Version 1.0
 */
@Service
public class PublicServiceImpl extends ServiceImpl<PublicMapper, Public> implements PublicService {
}
