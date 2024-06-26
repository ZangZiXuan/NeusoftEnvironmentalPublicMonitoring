package com.example.springcloudpolicymaker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.entity.PolicyMaker;
import com.example.springcloudpolicymaker.mapper.PolicyMakerMapper;
import com.example.springcloudpolicymaker.service.PolicyMakerService;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/19 14:18
 * @Version 1.0
 */
@Service
public class PolicyMakerServiceImpl extends ServiceImpl<PolicyMakerMapper, PolicyMaker> implements PolicyMakerService {
}
