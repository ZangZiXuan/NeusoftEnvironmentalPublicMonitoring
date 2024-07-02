package com.example.springcloudapi;

import com.example.springcloudapi.utils.CommUtil;
import com.example.springcloudapi.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringcloudApiApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void MD5() {
        String s = "123456";

        System.out.println("---------------");
        System.out.println(MD5Util.encode(s));
        System.out.println("---------------");
    }
    @Test
    void Time() {

        Date nowDateAsDate = CommUtil.getNowDateAsDate();
        System.out.println(nowDateAsDate);
    }
}
