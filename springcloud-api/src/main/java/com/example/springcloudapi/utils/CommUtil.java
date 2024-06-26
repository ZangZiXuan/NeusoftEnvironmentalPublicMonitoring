package com.example.springcloudapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class CommUtil {
    public static Date getNowDateLongStr(String dateFormat) {
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();

        // 创建一个日期格式化对象，使用传入的日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        // 格式化当前日期为字符串
        String formattedDate = now.format(formatter);


        // 将格式化后的字符串转换为 Date 对象
        Date date = java.sql.Timestamp.valueOf(formattedDate);

        return date;
    }
}
