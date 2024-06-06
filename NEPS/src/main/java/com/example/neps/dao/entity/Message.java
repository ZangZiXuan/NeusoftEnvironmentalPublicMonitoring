package com.example.neps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Message {
    private String id;
    private String userId;
    private String province;
    private String city;
    private String level;
    private Date date;
}
