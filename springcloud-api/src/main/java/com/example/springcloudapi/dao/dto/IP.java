package com.example.springcloudapi.dao.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ip object to operate database to check whether the ip is in the database.
 * Used by digital screen ip check
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ip_info")
public class IP {
    @TableId
    private String ipAddress;
}
