package com.example.springcloudapi.dao.dto;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/24 21:11
 * @Version 1.0
 */
public class AuthResult {
    private String tableName;
    private Object user;

    public AuthResult(String tableName, Object user) {
        this.tableName = tableName;
        this.user = user;
    }

    public String getTableName() {
        return tableName;
    }

    public Object getUser() {
        return user;
    }
}
