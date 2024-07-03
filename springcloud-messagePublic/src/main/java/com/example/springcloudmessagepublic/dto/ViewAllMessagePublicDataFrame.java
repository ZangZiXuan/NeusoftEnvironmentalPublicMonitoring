package com.example.springcloudmessagepublic.dto;

import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.entity.Public;
import com.example.springcloudmessagemanager.dto.ViewPageDTO;
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
    private ViewPageDTO viewPageDTO;
    private MessagePublic messagePublic;
    private Public aPublic;
}
