package org.featx.templet.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

import static org.featx.spec.constant.Symbol.POUND;

/**
 * @author Excepts
 * @since 2020/4/4 16:58
 */
@Slf4j
public class CustomCacheManager extends RedisCacheManager {

    public CustomCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @NotNull
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(cacheConfig.getTtl())
                .serializeKeysWith(cacheConfig.getKeySerializationPair())
                .serializeValuesWith(cacheConfig.getValueSerializationPair())
                .disableCachingNullValues();
        try {
            int index = name.indexOf(POUND);
            log.info("redis name {}", name);
            String time = name.substring(index + 1);
            if (index >= 0) {
                log.info("time {}", time);
                configuration = configuration.entryTtl(Duration.ofSeconds(Integer.valueOf(time)));
            }
        } catch (Exception e) {
            log.error("createRedisCache error", e);
        }
        return super.createRedisCache(name, configuration);
    }
}
