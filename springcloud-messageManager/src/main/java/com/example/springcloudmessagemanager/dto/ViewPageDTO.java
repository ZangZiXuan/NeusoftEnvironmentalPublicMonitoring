package com.example.springcloudmessagemanager.dto;

import com.example.springcloudapi.dao.entity.Griddler;
import com.example.springcloudapi.dao.entity.MessageGriddler;
import com.example.springcloudapi.dao.entity.MessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/3 14:13
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewPageDTO {
    private MessageManager messageManager;
    private MessageGriddler messageGriddler;
    private Griddler griddler;
    private String s;


    public ViewPageDTO(MessageManager messageManager,Griddler griddler, String s) {
        this.messageManager = messageManager;
        this.griddler = griddler;
        this.s = s;
    }
}
