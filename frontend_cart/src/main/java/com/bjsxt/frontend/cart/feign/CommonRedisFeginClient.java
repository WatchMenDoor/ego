package com.bjsxt.frontend.cart.feign;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.frontend.cart.feign
 * @version: 1.0
 */
@FeignClient("common-redis")
public interface CommonRedisFeginClient {

    //-------------------------/redis/cart
    @RequestMapping("/redis/cart/insertCart")
    void insertCart(@RequestBody Map<String,Object> map);

    @RequestMapping("/redis/cart/selectCart")
    Map<String, CartItem> selectCart(@RequestParam("userId") String  userId);
    //------------------/sso/redis
    @RequestMapping("/sso/redis/checkUserToken")
    TbUser checkUserToken(@RequestParam("token") String token);
}
