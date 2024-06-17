package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 10:56
 * @Version 1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("provinces")
public class Province {
    private String id;
    private String provinceName;
    private String shortTitle;
}
