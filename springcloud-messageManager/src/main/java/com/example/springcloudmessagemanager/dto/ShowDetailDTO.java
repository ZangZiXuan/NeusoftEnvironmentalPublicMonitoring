package com.example.springcloudmessagemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/1 11:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDetailDTO {
    private String provinceName;
    private String cityName;
    private int level;
    private String address;
    private Date date;
}
