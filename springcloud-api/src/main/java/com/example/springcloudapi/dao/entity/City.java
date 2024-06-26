package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String cityId;
    private String cityName;
    private String provinceId;
    @TableField("is_capital_city")
    private int isCapitalCity;
    @TableField("is_big_city")
    private int isBigCity;
}
