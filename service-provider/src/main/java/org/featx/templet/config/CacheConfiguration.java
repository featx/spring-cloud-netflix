package org.featx.templet.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Excepts
 * @since 2020/4/4 16:14
 */

@Configuration
@EnableAspectJAutoProxy
@Slf4j
public class CacheConfiguration extends CachingConfigurerSupport {

    private Integer cacheAliveMinutes = 30;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(cacheAliveMinutes))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

//        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
//        return cacheManager;

        return new CustomCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory), config);
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(@NotNull RuntimeException e, @NotNull Cache cache, @NotNull Object key) {
                log.error("GetCacheError of key {}", key, e);
            }

            @Override
            public void handleCachePutError(@NotNull RuntimeException e, @NotNull Cache cache, @NotNull Object key, Object value) {
                log.error("PutCacheError of key {}", key, e);
            }

            @Override
            public void handleCacheEvictError(@NotNull RuntimeException e, @NotNull Cache cache, @NotNull Object key) {
                log.error("EvictCacheError of key {}", key, e);
            }

            @Override
            public void handleCacheClearError(@NotNull RuntimeException e, @NotNull Cache cache) {
                log.error("ClearCacheError ", e);
            }
        };
    }

}
