package com.example.springcloudapi.dao;

/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/15 18:38
 * @Version 1.0
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**网络通信实现序列化接口*/
@Data
@NoArgsConstructor
@Accessors(chain = true) //开启链式写法
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    /**db_source表示s数据所属的数据库*/
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
}
