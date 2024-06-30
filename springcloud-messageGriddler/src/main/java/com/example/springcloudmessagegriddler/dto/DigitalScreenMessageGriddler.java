package com.example.springcloudmessagegriddler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/30 10:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalScreenMessageGriddler {
    private String griddlerId;
    private int aqiLevel;
    private Date dateTime;
    private String provinceId;
    private String cityId;

}
