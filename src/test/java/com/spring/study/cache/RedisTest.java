package com.spring.study.cache;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zmm on 2019-02-15
 */
public class RedisTest {
    private static long lockTimeOut = 100L;
    private static String LOCK_SUCCESS = "OK";
    private static Long RELEASE_SUCCESS = 1L;
    private static int a = 1;

    public static String getRedis(String key, String requestId, Jedis jedis) {
        return jedis.get(key);
    }

    public static String setRedis(String key, String requestId, Jedis jedis) {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jedis.set(key, requestId,"NX","PX",500L);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 100, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 1; i < 50; i++) {
            Task111 task = new Task111();
            executor.execute(task);
        }
        executor.shutdown();
    }
}


class Task111 implements Runnable {

    private static String host = "10.10.11.238";
    private static Integer port = 6379;
    private Jedis jedis = new Jedis(host, port);

//    private static String host = "10.10.4.121";
//    private static Integer port = 6370;
//    private Jedis jedis = new Jedis(host, port);

    @Override
    public void run() {
        jedis.auth("SjhkHD3J5k6H8SjSbK3SC");
        if (RedisTest.getRedis("1235", "123", jedis) == null) {
            RedisTest.setRedis("1235", "123", jedis);
        }
    }
}