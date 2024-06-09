package com.example.neps.dao.entity;

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
 * @Date 2024/6/6 15:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("message")
public class Message {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("province")
    private String province;
    @TableField("city")
    private String city;
    @TableField("level")
    private String level;
    @TableField("time")
    private Date date;

    @TableField("detail")
    private String detail;
    @TableField("address")
    private String address;
}
