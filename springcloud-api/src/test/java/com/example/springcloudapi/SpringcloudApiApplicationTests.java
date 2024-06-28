package com.example.springcloudapi;

import com.example.springcloudapi.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
