package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.CartService;
import com.bjsxt.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.common.redis.controller
 * @version: 1.0
 */

//用户登录状态下购物车的操作
@RestController
@RequestMapping("/redis/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //将购物车缓存到redis中
    @RequestMapping("/insertCart")
    public void insertCart(@RequestBody Map<String,Object> map){

        this.cartService.insertCart(map);
    }

    //根据用户的id查询购物车内容
    @RequestMapping("/selectCart")
    public Map<String, CartItem> selectCart(@RequestParam String  userId){

        return this.cartService.selectCart(userId);
    }
}
