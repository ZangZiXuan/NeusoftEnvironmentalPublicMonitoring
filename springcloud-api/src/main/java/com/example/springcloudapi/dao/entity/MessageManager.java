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
    private int provinceId;
    private int cityId;

}
