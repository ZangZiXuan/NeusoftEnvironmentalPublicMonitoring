package com.example.springcloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 17:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("griddler")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Griddler {
    private String id;
    private String name;
    private String code;
    private String password;
    private String provinceId;
    private String cityId;
    private String phone;
    /**
     * 网格员状态
     * 0：可工作状态
     * 1：临时抽调
     * 2：休假
     * 3：其他
     */
    private String statuses;
    private String remarks;
}
