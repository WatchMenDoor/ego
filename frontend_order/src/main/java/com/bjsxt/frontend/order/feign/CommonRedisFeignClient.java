package com.bjsxt.frontend.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.order.feign
 * @version: 1.0
 */
@FeignClient("common-redis")
public interface CommonRedisFeignClient {

    //-------------/redis/order
    @RequestMapping("/redis/order/selectOrderId")
     Long selectOrderId();
}
