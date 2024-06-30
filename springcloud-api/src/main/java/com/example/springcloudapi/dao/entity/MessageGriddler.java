package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 18:03
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("message_griddler")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessageGriddler {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("message_public_id")
    private String messagePublicId;
    private String griddlerId;
    private int so2;
    private int co;
    private int pm;
    private Date time;
    /**
     * 待确认：0，已确认1
     */
    private int status;
    private int aqiLevel;
}
