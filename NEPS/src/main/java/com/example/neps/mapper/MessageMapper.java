package com.example.neps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.neps.dao.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/7 9:28
 * @Version 1.0
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {
}
