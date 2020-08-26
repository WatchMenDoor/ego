package com.bjsxt.frontend.order.service;

import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.Result;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.order.service
 * @version: 1.0
 */
public interface OrderService {

    public Result insertOrder(List<TbOrderItem> orderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping);
}
