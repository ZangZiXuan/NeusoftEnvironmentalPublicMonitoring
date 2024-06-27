package com.example.springcloudapi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/27 11:08
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AQIDTO {
    private int aqiLevel;
    private String description;
    private int countNum;
}
