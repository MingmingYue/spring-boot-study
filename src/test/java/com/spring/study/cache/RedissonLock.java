package com.spring.study.cache;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Created by zmm on 2019-02-19
 */
public class RedissonLock {

    public static void getLock() {
        Config config = new Config();
        config.useSentinelServers().addSentinelAddress("10.10.11.238:6379", "10.10.4.121:6370").setPassword("SjhkHD3J5k6H8SjSbK3SC").setDatabase(0);

        RedissonClient redissonClient = Redisson.create(config);
        RLock redLock = redissonClient.getLock("REDLOCK_KEY");

        boolean isLock;
        try {
            isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
            if (isLock) {

            }
        } catch (Exception e) {
        } finally {
            redLock.unlock();
        }
    }


}
