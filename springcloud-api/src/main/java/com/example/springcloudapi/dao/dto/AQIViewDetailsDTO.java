package com.example.springcloudapi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/7 13:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AQIViewDetailsDTO {
    private String id;
    private String provinceName;
    private String cityName;
    private String address;
    private int aqiLevel;
    private String aqiDescription;
    private Date time;
    private String publicName;
    private String publicPhone;
    private String griddlerName;
    private String griddlerPhone;
    private String description;

}
