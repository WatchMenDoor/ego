package com.bjsxt.frontend.order.service.impl;

import com.bjsxt.frontend.order.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.order.feign.FrontendCartFeignClient;
import com.bjsxt.frontend.order.service.OrderService;
import com.bjsxt.mapper.TbOrderItemMapper;
import com.bjsxt.mapper.TbOrderMapper;
import com.bjsxt.mapper.TbOrderShippingMapper;
import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.order.service.impl
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Autowired
    private FrontendCartFeignClient frontendCartFeignClient;

    //创建订单
    @Override
    @LcnTransaction
    public Result insertOrder(List<TbOrderItem> orderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping) {
        //获取订单id
        Long orderId = this.commonRedisFeignClient.selectOrderId();

        Date date = new Date();
        //补齐Tborder数据
        tbOrder.setOrderId(orderId.toString());
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        tbOrder.setStatus(1);
        //0-未评价 1-已评价
        tbOrder.setBuyerRate(0);
        this.tbOrderMapper.insert(tbOrder);
        //处理TbOrderItem
        for (TbOrderItem item: orderItem) {
            item.setOrderId(orderId.toString());
            item.setId(IDUtils.genItemId()+"");
            this.tbOrderItemMapper.insert(item);
            //将商品从购物车中删去
            this.frontendCartFeignClient.deleteItemFromCart(tbOrder.getUserId().toString(), Long.parseLong(item.getItemId()));
        }

        //处理tbOrderShipping
        tbOrderShipping.setCreated(date);
        tbOrderShipping.setOrderId(orderId.toString());
        tbOrderShipping.setUpdated(date);
        this.tbOrderShippingMapper.insert(tbOrderShipping);



        return Result.ok(orderId);
    }
}
