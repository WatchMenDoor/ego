package com.bjsxt.frontend.cart.service;

import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.cart.service
 * @version: 1.0
 */
public interface CookieCartService {


    Result addItem(Long itemId,Integer num, HttpServletRequest request, HttpServletResponse response);

    Result showCart(String userId,HttpServletRequest request,HttpServletResponse response);

    Result updateItemNum(Integer num,String userId,Long itemId,HttpServletRequest request,HttpServletResponse response);

    Result deleteItemFromCart(String userId,Long itemId,HttpServletRequest request,HttpServletResponse response);
}
