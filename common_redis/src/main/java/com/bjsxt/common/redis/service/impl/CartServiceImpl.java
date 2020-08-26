package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.CartService;
import com.bjsxt.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.common.redis.service.impl
 * @version: 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_cart_redis_key}")
    private String frontend_cart_redis_key;
    @Override
    public void insertCart(Map<String, Object> map) {
        String userid =(String) map.get("userId");
        Map<String, CartItem> cart=(Map<String, CartItem> ) map.get("cart");
        this.redisTemplate.opsForHash().put(this.frontend_cart_redis_key,userid,cart);
    }


    //根据用户的id查询购物车内容
    @Override
    public Map<String, CartItem> selectCart(String userId) {
        return (Map<String, CartItem>)this.redisTemplate.opsForHash().get(this.frontend_cart_redis_key, userId);
    }
}
