package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 10:57
 * @Version 1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("cities")
public class City {
    private String id;
    private String cityName;
    private String provinceId;
}
