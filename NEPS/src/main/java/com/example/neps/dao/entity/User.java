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

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/6 15:25
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("monitor")
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("phone")
    private String phone;
    @TableField("age")
    private Integer age;
    @TableField("sex")
    private Integer sex;
    @TableField("password")
    private String password;


}
