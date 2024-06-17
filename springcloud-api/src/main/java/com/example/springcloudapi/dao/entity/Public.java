package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Date 2024/6/15 18:30
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("public")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Public {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String phone;
    private String name;
    private Date birthday;
    /**
     * 性别
     * 0：男
     * 1：女
     */
    private int gender;
    private String password;

}
