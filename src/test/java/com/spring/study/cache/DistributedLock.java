package com.spring.study.cache;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * redis {
 * host = "10.10.4.121"
 * port = 6370
 * password = "SjhkHD3J5k6H8SjSbK3SC"
 * pool {
 * max-wait = 5000
 * max-active = 20
 * max-idle = 500
 * min-idle = 20
 * }
 * timeout = 3000
 * }
 * <p>
 * cache.dataCacheExpireTime = 10800
 * Created by zmm on 2019-02-14
 */
public class DistributedLock {

    private static long lockTimeOut = 100L;
    private static String LOCK_SUCCESS = "OK";
    private static Long RELEASE_SUCCESS = 1L;

    private static String host = "10.10.11.238";
    private static Integer port = 6379;

    public static boolean tryLock(String key, String requestId, Jedis jedis) {
        String result = jedis.set(key, requestId, "NX", "PX", lockTimeOut);
        return LOCK_SUCCESS.equals(result);
    }

    public static boolean releaseDistributedLock(String key, String requestId, Jedis jedis) {
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(requestId));
        return RELEASE_SUCCESS.equals(result);
    }

    static int j = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    String uuid = "123";
                    Jedis jedis = new Jedis(host, port);
                    jedis.auth("SjhkHD3J5k6H8SjSbK3SC");
                    while (true) {
                        if (DistributedLock.tryLock("1231", uuid, jedis)) {
                            Thread.sleep(80); // 处理业务时间
                            System.out.println(Thread.currentThread().getName() + ": " + j++);
                            break;
                        }
                        Thread.sleep(50); // 休眠一段时间
                    }
                    System.out.println(DistributedLock.releaseDistributedLock("1231", "123", jedis));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}