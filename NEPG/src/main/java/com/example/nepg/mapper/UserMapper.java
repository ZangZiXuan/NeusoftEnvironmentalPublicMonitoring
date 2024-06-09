package com.example.nepg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nepg.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/9 15:00
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
