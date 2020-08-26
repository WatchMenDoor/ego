package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.service.impl
 * @version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_ad_redis_key}")
    private String frontend_ad_redis_key;
    @Override
    public void insertContent(List<Map> map) {
        this.redisTemplate.opsForValue().set(this.frontend_ad_redis_key, map);
    }

    @Override
    public List<Map> selectContentAD() {
        return (List<Map>) this.redisTemplate.opsForValue().get(this.frontend_ad_redis_key);
    }
}
