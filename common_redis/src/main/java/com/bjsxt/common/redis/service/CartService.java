package com.bjsxt.common.redis.service;

import com.bjsxt.utils.CartItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.common.redis.service
 * @version: 1.0
 */
public interface CartService {

    void insertCart( Map<String,Object> map);

    Map<String, CartItem> selectCart(String  userId);
}
