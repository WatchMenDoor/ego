package com.bjsxt.frontend.order.feign;

import com.bjsxt.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.order.feign
 * @version: 1.0
 */
@FeignClient("frontend-cart")
public interface FrontendCartFeignClient {
     //--------------------/cart
    @RequestMapping("/cart/deleteItemFromCart")
    public Result deleteItemFromCart(@RequestParam("userId") String userId, @RequestParam("itemId") Long itemId);
}
