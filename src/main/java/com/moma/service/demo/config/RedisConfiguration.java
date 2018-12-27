package com.moma.service.demo.config;

import com.moma.zoffy.helper.JacksonHelper;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfiguration
 *
 * <p>Redis Cache Configuration
 *
 * <p>1.Support Multi Cache Space
 *
 * <p>2.Custom Json Serializer
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/25/18 - 2:45 PM.
 */
// @SpringBootConfiguration
public class RedisConfiguration extends CachingConfigurerSupport {
  public static final Long NANO_ADJUSTMENT = 2L;

  @Value("${cache.default.expire-time:1800}")
  private int defaultExpireTime;

  @Value("${cache.dev.expire-time:800}")
  private int devExpireTime;

  @Value("${cache.dev.name:dev}")
  private String devCacheName;

  /**
   * @author Created by ivan on 7:11 PM 12/25/18.
   *     <p>Configuration Cache Manager
   * @param redisConnectionFactory :
   * @return org.springframework.cache.CacheManager
   */
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    /*Default Configuration */
    RedisCacheConfiguration redisConfiguration = RedisCacheConfiguration.defaultCacheConfig();
    redisConfiguration =
        redisConfiguration
            .entryTtl(Duration.ofSeconds(defaultExpireTime, NANO_ADJUSTMENT))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    jackson2JsonRedisSerializer()))
            .disableCachingNullValues();

    /*Custom Cache Space*/
    Set<String> cacheNames = new HashSet<>();
    cacheNames.add(devCacheName);
    /*Custom Cache Configuration*/
    Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
    configurationMap.put(
        devCacheName,
        redisConfiguration.entryTtl(Duration.ofSeconds(devExpireTime, NANO_ADJUSTMENT)));

    /*Set Manager*/
    RedisCacheManager redisCacheManager =
        RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(redisConfiguration)
            .initialCacheNames(cacheNames)
            .withInitialCacheConfigurations(configurationMap)
            .build();
    return redisCacheManager;
  }

  /**
   * @author Created by ivan on 7:11 PM 12/25/18.
   *     <p>Specify Value Serializer
   * @return org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
   */
  private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<Object>(Object.class);
    jackson2JsonRedisSerializer.setObjectMapper(JacksonHelper.getObjectMapper());
    return jackson2JsonRedisSerializer;
  }
}
