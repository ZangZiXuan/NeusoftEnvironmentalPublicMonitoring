package com.example.springcloudgriddler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudgriddler.mapper.GriddlerMapper;
import com.example.springcloudgriddler.service.GriddlerService;
import org.springframework.stereotype.Service;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/19 14:19
 * @Version 1.0
 */
@Service
public class GriddlerServiceImpl extends ServiceImpl<GriddlerMapper, Griddler> implements GriddlerService {

}
