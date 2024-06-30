package com.example.springcloudmessagepublic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/30 21:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalMessagePublicDTO {
    private String provinceName;
    private String cityName;
    private String address;
    private Date date;
    private String publicId;
    private Integer aqiLevel;
    private String description;
}
