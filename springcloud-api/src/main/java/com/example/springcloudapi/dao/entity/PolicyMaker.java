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
 * @Date 2024/6/15 18:27
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("policymaker")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PolicyMaker {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String username;
    private String password;

}
