package com.example.springcloudmessagepublic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/30 22:41
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewAllMessagePublicDataFrame {
    private String messageId;
    private String publicName;
    private String provinceName;
    private String cityName;
    private int level;
    private String date;
    private String time;


}
