package com.example.neusoftgateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.dto.IP;
import com.example.neusoftgateway.mapper.IPMapper;
import com.example.neusoftgateway.service.IPService;
import org.springframework.stereotype.Service;

@Service
public class IPServiceImpl extends ServiceImpl<IPMapper, IP> implements IPService {
}
