package com.example.springcloudmessagepublic.dto;

import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.entity.Public;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/3 11:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePublicNumDTO {
    private Public aPublic;
    private MessagePublic messagePublic;
    private String provinceName;
    private String cityName;
    private String shortTitle;
    private int sumRecords;
}
