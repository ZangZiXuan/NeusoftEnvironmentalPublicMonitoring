package com.example.springcloudmessagegriddler.dto;

import com.example.springcloudapi.dao.entity.MessageGriddler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/7 9:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageGriddlerViewDTO {
    private MessageGriddler messageGriddler;
    private String provinceId;
    private String cityId;
    private String griddlerName;
    private String publicName;
}
