package com.example.springcloudapi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/16 11:18
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessagePublicDTO {
    private MessagePublic messagePublic;
    private String provinceName;
    private String cityName;
    private String shortTitle;
}
