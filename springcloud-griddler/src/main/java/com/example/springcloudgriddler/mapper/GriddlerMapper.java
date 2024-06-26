package com.example.springcloudgriddler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springcloudapi.dao.entity.Griddler;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 19:40
 * @Version 1.0
 */
@Mapper
public interface GriddlerMapper  extends BaseMapper<Griddler> {
}
