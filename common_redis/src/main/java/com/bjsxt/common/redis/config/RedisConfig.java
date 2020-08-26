package com.bjsxt.common.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.config
 * @version: 1.0
 */
@Configuration
public class RedisConfig {

    //配置序列化器
    @Bean
    public RedisTemplate<String,Object> stringRedisTemplate(RedisConnectionFactory factory){
        //创建redistemplate
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //设置序列化器
        //value的序列化处理
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //key的序列化处理
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //设置redis中的string类型的value序列化器
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //设置redis中的hash类型的value序列化器
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);


        //设置redis中的string类型的key序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //设置redis中的hash类型的key序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }
}
