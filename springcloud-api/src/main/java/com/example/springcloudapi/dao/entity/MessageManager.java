package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 18:18
 * @Version 1.0
 */
@Data
@TableName("message_manager")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MessageManager {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String messageId;
    private String griddlerId;
    private int statusManager;
    private String provinceId;
    private String cityId;
    private int status;

    public MessageManager(String messageId, String griddlerId, int statusManager, String provinceId, String cityId, int status) {
        this.messageId = messageId;
        this.griddlerId = griddlerId;
        this.statusManager = statusManager;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.status = status;
    }
}
