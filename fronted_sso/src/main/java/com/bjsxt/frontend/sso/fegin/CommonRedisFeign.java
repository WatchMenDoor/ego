package com.bjsxt.frontend.sso.fegin;

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
 * @Description: com.bjsxt.frontend.sso.fegin
 * @version: 1.0
 */
@FeignClient("common-redis")
public interface CommonRedisFeign {
     //-----------------------------/sso/redis
    @RequestMapping("/sso/redis/insertUser")
     void insertUser(@RequestBody TbUser tbUser, @RequestParam("token") String Token);

    @RequestMapping("/sso/redis/removeUser")
    void removeUser(@RequestParam("token") String Token);





    //---------------------------/redis/cart

    @RequestMapping("/redis/cart/insertCart")
     void insertCart(@RequestBody Map<String,Object> map);

    @RequestMapping("/redis/cart/selectCart")
     Map<String, CartItem> selectCart(@RequestParam("userId") String  userId);
}
