package com.bjsxt.frontend.cart.service;

import com.bjsxt.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.frontend.cart.service
 * @version: 1.0
 */

public interface RedisCartService {
    Result addItem(Long itemId, Integer num,String userId);

    Result showCart(String userId);

    Result updateItemNum(Integer num,String userId,Long itemId);

    Result deleteItemFromCart(String userId,Long itemId);

    Result goSettlement(String userId,String[] ids);
}
