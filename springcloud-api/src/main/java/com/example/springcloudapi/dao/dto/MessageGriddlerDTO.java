package com.example.springcloudapi.dao.dto;

import com.example.springcloudapi.dao.entity.MessageGriddler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/19 21:45
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageGriddlerDTO extends MessageGriddler {
    private String provinceId;
    private String provinceName;
    private String provinceShortTitle;
    private Integer soNum;
    private Integer coNum;
    private Integer pmNum;
    private Integer AQINum;

}
