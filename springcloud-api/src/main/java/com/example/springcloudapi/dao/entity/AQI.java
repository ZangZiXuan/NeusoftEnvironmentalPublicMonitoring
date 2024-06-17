package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * 空气质量指数级别
 * @Date 2024/6/15 17:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("aqi")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AQI {

    private String aqiId;
    private String description;
    private String apiDescription;
    private String color;
    private String healthImpact;
    private String measures;
    private int so2Min;
    private int so2Max;
    private int coMin;
    private int coMax;
    private int pmMin;
    private int pmMax;
    private String remarks;
}
