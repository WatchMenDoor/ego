package com.bjsxt.frontend.order.controller;

import com.bjsxt.frontend.order.service.OrderService;
import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.JsonUtils;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.order.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/insertOrder")
    public Result insertOrder(String orderItem, TbOrder tbOrder,TbOrderShipping tbOrderShipping){
        try{
            JsonUtils.jsonToList(orderItem, TbOrderItem.class);
            Result res = Result.formatObjectToList(orderItem, TbOrderItem.class);
            List<TbOrderItem> list =(List<TbOrderItem>) res.getData();
            return this.orderService.insertOrder(list, tbOrder, tbOrderShipping);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
