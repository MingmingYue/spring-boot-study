package com.spring.study.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 *
 * @author: ZhouMingming
 * @data: Create on 2018/7/30.
 */
@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "dataCache")
@EnableConfigurationProperties
public class CacheConfig {

    private static final int DEFAULT_TTL = 10;
    private static final int DEFAULT_MAXSIZE = 100;

    private Map<String, Map<String, Integer>> configs = new ConcurrentHashMap<>();

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> config : configs.entrySet()) {
            caches.add(new CaffeineCache((config.getKey()), Caffeine.newBuilder()
                    .expireAfterWrite(config.getValue().getOrDefault("expireAfterWrite", DEFAULT_TTL), TimeUnit.SECONDS)
                    .maximumSize(config.getValue().getOrDefault("maximumSize", DEFAULT_MAXSIZE))
                    .build()));
        }

        cacheManager.setCaches(caches);
        return cacheManager;
    }

    public Map<String, Map<String, Integer>> getConfig() {
        return configs;
    }
}
