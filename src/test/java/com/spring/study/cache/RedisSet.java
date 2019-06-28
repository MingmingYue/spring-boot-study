package com.spring.study.cache;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * Created by zmm on 2019-03-05
 */
public class RedisSet {

    private static String host = "10.10.11.238";
    private static Integer port = 6379;

    private static void setRedis(Jedis jedis, String... value) {
        jedis.sadd("2", value);
    }

    private static void hashSetRedis(Jedis jedis, HashMap<String, String> mm) {
        jedis.hmset("hash", mm);
    }

    private static void zscanSetRedis(Jedis jedis, HashMap<String, Double> mm) {
        jedis.zadd("zadd", mm);
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis(host, port);
        jedis.auth("SjhkHD3J5k6H8SjSbK3SC");
        HashMap<String, Double> mm = new HashMap<>();
        mm.put("12", 123d);
        mm.put("32", 19d);
        zscanSetRedis(jedis, mm);
    }
}
