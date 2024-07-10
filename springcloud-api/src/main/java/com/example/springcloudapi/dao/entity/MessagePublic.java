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
 * @Date 2024/6/15 18:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("message_public")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessagePublic {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String publicId;
    private String provinceId;
    private String cityId;
    private String address;
    private int level;

    private String description;
    private Date date;
    /**
     * 状态：
     * 待指派：0
     * 已指派：1
     */
    private int status;
    /**
     * 提交图片的地址
     */
    private String photo;

    public MessagePublic(String id, String publicId, String provinceId, String cityId, String address) {
        this.id = id;
        this.publicId = publicId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.address = address;
    }

    public MessagePublic(String id, String publicId, String provinceId, String cityId, String address, int level, String description, Date date, int status) {
        this.id = id;
        this.publicId = publicId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.address = address;
        this.level = level;
        this.description = description;
        this.date = date;
        this.status = status;
    }
}
