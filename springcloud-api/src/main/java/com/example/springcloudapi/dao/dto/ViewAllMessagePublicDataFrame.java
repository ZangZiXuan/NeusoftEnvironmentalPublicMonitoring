package com.example.springcloudapi.dao.dto;

import com.example.springcloudapi.dao.entity.MessagePublic;
import com.example.springcloudapi.dao.entity.Public;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
