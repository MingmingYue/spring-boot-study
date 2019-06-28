package com.spring.study.cache;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;

/**
 * Created by zmm on 2019-03-05
 */
public class RedisList {

    private static String host = "10.10.11.238";
    private static Integer port = 6379;

    private static void setList(Jedis jedis, String key, String... value) {
        jedis.rpush(key, value);
    }

    private static List<String> getList(Jedis jedis, String key) {
        return jedis.lrange(key, 0, 10);
    }

    private static void ltrimList(Jedis jedis, String key, int start, int end) {
        jedis.ltrim(key, start, end);
    }

    private static void delKey(Jedis jedis, String key) {
        jedis.del(key);
    }

    private static void getKey(Jedis jedis, String key) {
        System.out.println(jedis.get(key));
    }


    private static void scanView(Jedis jedis) {
        String scanRet = "0";
        do {
            ScanResult ret = jedis.scan(scanRet, new ScanParams().match("df:*"));
            scanRet = ret.getStringCursor();
            System.out.println();
            System.out.println();
            ret.getResult().forEach(ss -> System.out.println(ss.toString() + ": " + getList(jedis, ss.toString())));
        } while (!scanRet.equals("0"));
    }

    private static void sscanView(Jedis jedis) {
        String scanRet = "0";
        do {
            ScanResult ret = jedis.sscan("2", scanRet, new ScanParams().match("da*"));
            scanRet = ret.getStringCursor();
            System.out.println();
            System.out.println();
            ret.getResult().forEach(ss -> System.out.println(ss.toString()));
        } while (!scanRet.equals("0"));
    }

    private static void hscanView(Jedis jedis) {
        String scanRet = "0";
        do {
            ScanResult ret = jedis.hscan("hash", scanRet, new ScanParams().match("12*"));
            scanRet = ret.getStringCursor();
            System.out.println();
            System.out.println();
            ret.getResult().forEach(ss -> System.out.println(ss.toString()));
        } while (!scanRet.equals("0"));
    }

    private static void zscanView(Jedis jedis) {
        String scanRet = "0";
        do {
            ScanResult ret = jedis.zscan("zadd", scanRet, new ScanParams().match("1*"));
            scanRet = ret.getStringCursor();
            System.out.println();
            System.out.println();
            ret.getResult().forEach(ss -> System.out.println(ss.toString()));
        } while (!scanRet.equals("0"));
    }

    private static void scan(Jedis jedis) {
        String scanRet = ScanParams.SCAN_POINTER_START;
        do {
            ScanResult ret = jedis.scan(scanRet, new ScanParams().match("df:*"));
            scanRet = ret.getStringCursor();
            ret.getResult().forEach(ss -> {
                List<String> allValues = getList(jedis, ss.toString());
                long length = allValues.stream().filter(values -> {
                    String[] value = values.split("_");
                    try {
                        if (StringUtils.isNumeric(value[2])) {
                            if (Integer.valueOf(value[2]) > 1552021667) {
                                return false;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                    return true;
                }).count();
                if ((int) length < allValues.size()) {
                    ltrimList(jedis, ss.toString(), (int) length, allValues.size());
                } else {
                    delKey(jedis, ss.toString());
                }
            });
        } while (!scanRet.equals("0"));
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis(host, port);
        jedis.auth("SjhkHD3J5k6H8SjSbK3SC");
//        String key = "df:b217ddf4-cf1b-47f5-9318-54ad41aa5ed6";
//        for (int i = 0; i < 100; i++) {
//            setList(jedis, key, String.valueOf(i));
//        }
//        int allLength = getList(jedis, key).size();
//        int length = getList(jedis, key).stream().filter(ss -> {
//            if (Integer.valueOf(ss) > 20) return false;
//            else return true;
//        }).collect(Collectors.toList()).size();
//        ltrimList(jedis, key, length, allLength);
//        System.out.println(getList(jedis, key));

//        scan(jedis);
//        scanView(jedis);
        sscanView(jedis);
        hscanView(jedis);
        zscanView(jedis);
//        getKey(jedis, "*");
    }


}
