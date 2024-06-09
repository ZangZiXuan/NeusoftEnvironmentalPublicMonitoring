package com.example.neps.utils;

import java.security.MessageDigest;
import java.util.Objects;
/**
 * @Author Zang Xinrui
 * @Description TODO
 * @Date 2024/6/8 10:00
 * @Version 1.0
 */


public class MD5Util {
    private static final char[] HEX_DIGITS
            = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String characterEncoding;

    public static String encode(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Objects.nonNull(characterEncoding)
                    ? str.getBytes(characterEncoding) : str.getBytes());
            byte[] bytes = md.digest();
            StringBuilder builder = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                builder.append(HEX_DIGITS[b >> 4 & 15]);
                builder.append(HEX_DIGITS[b & 15]);
            }
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException("process failed ", e);
        }
    }
}
