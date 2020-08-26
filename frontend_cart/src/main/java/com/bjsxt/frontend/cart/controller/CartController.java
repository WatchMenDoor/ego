package com.bjsxt.frontend.cart.controller;

import com.bjsxt.frontend.cart.service.CookieCartService;
import com.bjsxt.frontend.cart.service.RedisCartService;
import com.bjsxt.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.cart.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private RedisCartService redisCartService;

    //添加购物车
    @RequestMapping("/addItem")
    public Result addItem(Long itemId, String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        try{

            if (StringUtils.isBlank(userId)){
                //在用户未登录状态下
                return  this.cookieCartService.addItem(itemId, num, request, response);
            }else{
                //在用户已经登录状态下
                return  this.redisCartService.addItem(itemId, num, userId);
            }


        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //查看购物车
    @RequestMapping("/showCart")
    public  Result showCart(String userId,HttpServletRequest request,HttpServletResponse response){
        try{

            if (StringUtils.isBlank(userId)){
                //在用户未登录状态下
              return this.cookieCartService.showCart(userId, request, response);
            }else{
                //在用户已经登录状态下
              return this.redisCartService.showCart(userId);
            }


        }catch (Exception e){

        }
        return Result.build(500, "error");
    }


    //修改购物车中商品的数量
    @RequestMapping("/updateItemNum")
    public  Result updateItemNum(Integer num,String userId,Long itemId,HttpServletRequest request,HttpServletResponse response){
        try{

            if (StringUtils.isBlank(userId)){
                //在用户未登录状态下
                return this.cookieCartService.updateItemNum(num, userId, itemId, request, response);
            }else{
                //在用户已经登录状态下
               return this.redisCartService.updateItemNum(num, userId, itemId);
            }


        }catch (Exception e){

        }
        return Result.build(500, "error");
    }


    //删除购物车中的商品
    @RequestMapping("/deleteItemFromCart")
    public  Result deleteItemFromCart(@RequestParam String userId,@RequestParam Long itemId,HttpServletRequest request,HttpServletResponse response){
        try{

            if (StringUtils.isBlank(userId)){
                //在用户未登录状态下
                 return this.cookieCartService.deleteItemFromCart(userId, itemId, request, response);
            }else{
                //在用户已经登录状态下
                return this.redisCartService.deleteItemFromCart(userId, itemId);
            }


        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //结算购物车中的商品
    @RequestMapping("/goSettlement")
    public  Result goSettlement(String userId,String[] ids){
        try{

           this.redisCartService.goSettlement(userId, ids);

        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
