package com.example.springcloudapi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 10:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {
    private String shortTitle;
    private String provinceId;
    private String cityId;



}
