package com.example.springcloudapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springcloudapi.dao.entity.Manager;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 19:41
 * @Version 1.0
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
}
