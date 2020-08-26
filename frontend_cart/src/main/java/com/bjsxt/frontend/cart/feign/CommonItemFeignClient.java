package com.bjsxt.frontend.cart.feign;

import com.bjsxt.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.cart.feign
 * @version: 1.0
 */
@FeignClient("common-item")
public interface CommonItemFeignClient {


    //------------------/service/item
    @RequestMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam Long itemId);
}
