package com.example.springcloudapi.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class CommUtil {
    public static String getNowDateLongStr() {
        // 获取当前日期和时间
        Date now = new Date();

        // 创建一个日期格式化对象，使用传入的日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化当前日期为字符串
        String formattedDate = formatter.format(now);

        return formattedDate;
    }

    public static Date getNowDateAsDate() {
        // 获取当前日期和时间
        Date now = new Date();

        // 创建一个日期格式化对象，使用传入的日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化当前日期为字符串
        String formattedDate = formatter.format(now);

        try {
            // 将格式化后的字符串解析为 Date 对象
            Date parsedDate = formatter.parse(formattedDate);
            return parsedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
