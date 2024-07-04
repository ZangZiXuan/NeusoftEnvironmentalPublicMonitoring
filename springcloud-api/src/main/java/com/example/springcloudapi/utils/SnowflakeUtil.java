package com.example.springcloudapi.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import xyz.downgoon.snowflake.Snowflake;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Snowflake id generator
 */
public class SnowflakeUtil {

    private static final Snowflake snowflake;

    static {
        Long workerId = getWorkId();
        Long datacenterId = getDatacenterId();
        snowflake = new Snowflake(datacenterId, workerId);
    }

    /**
     * generate id
     * @return the snowflake id
     */
    public static String genId() {
        return String.valueOf(snowflake.nextId());
    }

    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            return RandomUtils.nextLong(0,31);
        }
    }

    private static Long getDatacenterId(){
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }
}
