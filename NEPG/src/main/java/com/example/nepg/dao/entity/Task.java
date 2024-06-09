package com.example.nepg.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/9 14:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tasks")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Task {
    @TableField("id")
    private String id;
    @TableField("message_id")
    private String messageId;
    /**
     * 待检测(0)，已检测完成(1)两种状态
     */
    @TableField("status")
    private Integer status;
    @TableField("SO2")
    private Integer S02;
    @TableField("CO")
    private Integer CO;
    @TableField("PM2.5")
    private Integer PM;
    @TableField("SO2_level")
    private Integer SO2Level;
    @TableField("CO_level")
    private Integer COLevel;
    @TableField("PM2.5_level")
    private Integer PMLevel;

    public Task(String id, String messageId, Integer status) {
        this.id = id;
        this.messageId = messageId;
        this.status = status;
    }
}
