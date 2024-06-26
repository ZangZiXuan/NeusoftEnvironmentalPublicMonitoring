package com.example.springcloudapi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/24 19:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
