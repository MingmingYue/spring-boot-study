package com.spring.study.config;


import com.spring.study.SpringStudyServiceApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringStudyServiceApplication.class, RedisConfigTest.class})
public class RedisConfigTest {

    private static final Logger log = LogManager.getLogger(RedisConfigTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void redisCacheTemplate() {

        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        redisCacheTemplate.opsForValue().set(key, "12312312321321");
        // TODO 对应 String（字符串）
        final String string = (String) redisCacheTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", string);
    }
}
