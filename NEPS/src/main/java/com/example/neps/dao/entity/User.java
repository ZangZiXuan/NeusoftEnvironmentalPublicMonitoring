package com.example.neps.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/6 15:25
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("monitor")
public class User {
    @TableField("id")
    private String id;
    @TableField("phone")
    private String phone;
    @TableField("age")
    private Integer age;
    @TableField("sex")
    private Integer sex;
    @TableField("password")
    private String password;
    @TableField("province")
    private String province;
    @TableField("city")
    private String city;
    @TableField("address")
    private String address;

    public User(String id, String phone, Integer age, Integer sex, String password) {
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.password = password;
    }

}
