package com.example.springcloudmessagepublic.dto;

import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.entity.Public;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/7/2 21:29
 * @Version 1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessagePublicPageDTO {
    private Public aPublic;
    private MessagePublic messagePublic;
}
