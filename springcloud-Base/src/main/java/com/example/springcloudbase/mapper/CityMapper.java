package com.example.springcloudbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springcloudapi.dao.entity.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 10:59
 * @Version 1.0
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {
}
