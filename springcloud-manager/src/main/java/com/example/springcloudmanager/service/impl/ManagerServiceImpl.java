package com.example.springcloudmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.entity.Manager;
import com.example.springcloudmanager.mapper.ManagerMapper;
import com.example.springcloudmanager.service.ManagerService;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/19 14:17
 * @Version 1.0
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
}
