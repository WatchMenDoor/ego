package com.bjsxt.frontend.cart.service.impl;

import com.bjsxt.frontend.cart.feign.CommonItemFeignClient;
import com.bjsxt.frontend.cart.feign.CommonRedisFeginClient;
import com.bjsxt.frontend.cart.service.RedisCartService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.CartItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.frontend.cart.service.impl
 * @version: 1.0
 */

//用户登录状态下购物车操作
@Service
public class RedisCartServiceImpl implements RedisCartService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeginClient commonRedisFeginClient;

    //添加商品到购物车
    @Override
    public Result addItem(Long itemId, Integer num, String userId) {

        //1.查询商品
        TbItem tbItem = this.selectItemById(itemId);
        // 2.获取购物车
        Map<String, CartItem> cart = this.getCart(userId);
        // 3.将商品添加到购物车
        this.addItemToCart(cart,tbItem,num,itemId);
        // 4.将购物车添加到redis缓存中
        this.addClientToRedis(userId,cart);

        return Result.ok();
    }



    //查看购物车
    @Override
    public Result showCart(String userId) {
        List<CartItem> list = new ArrayList<>();
        Map<String, CartItem> cart = this.getCart(userId);
        Set<String> keys = cart.keySet();
        for (String key: keys) {
            list.add(cart.get(key));
        }


        return Result.ok(list);
    }

    //修改购物车中商品的数量
    @Override
    public Result updateItemNum(Integer num, String userId, Long itemId) {
        Map<String, CartItem> cart = this.getCart(userId);
        CartItem cartItem = cart.get(itemId);
        if (cartItem!=null){
            cartItem.setNum(num);
        }
        this.addClientToRedis(userId, cart);
        return Result.ok();
    }

    //删除购物车中的商品
    @Override
    public Result deleteItemFromCart(String userId, Long itemId) {
        Map<String, CartItem> cart = this.getCart(userId);
        cart.remove(itemId);
        this.addClientToRedis(userId, cart);
        return Result.ok();
    }

    //结算
    @Override
    public Result goSettlement(String userId, String[] ids) {
        //1.获取购物车
        Map<String, CartItem> cart = this.getCart(userId);
        //从购物车获取选中的商品
        List<CartItem> list = this.getItemList(cart,ids);

        return Result.ok(list);
    }



    //根据商品id查询商品
    private TbItem selectItemById(Long itemId) {
        return  this.commonItemFeignClient.selectItemInfo(itemId);
    }
    //根据用户id取出购物车
    private Map<String, CartItem> getCart(String userId) {
        try {
            Map<String, CartItem> cart = this.commonRedisFeginClient.selectCart(userId);
            //第一次登录查不到
            if (cart==null){
                cart = new HashMap<String, CartItem>();
            }
            return cart;
        }catch (Exception e){

        }
        return new HashMap<String, CartItem>();
    }
   //将商品添加到购物车
    private void addItemToCart(Map<String, CartItem> cart, TbItem item,Integer num, Long itemId) {

        //从购物车中取商品
        CartItem cItem = cart.get(itemId.toString());
        if (cItem==null){
            //没有相同的商品,直接创建
            CartItem cartItem = new CartItem();
            cartItem.setId(itemId);
            cartItem.setImage(item.getImage());
            cartItem.setPrice(item.getPrice());
            cartItem.setSellPoint(item.getSellPoint());
            cartItem.setNum(num);
            cartItem.setTitle(item.getTitle());
            cart.put(item.getId().toString(), cartItem);
        }else{
            //有相同的商品，在原数量上新增
            cItem.setNum(cItem.getNum()+num);
        }

    }
    //将购物车添加到redis缓存中
    private void addClientToRedis(String userId, Map<String, CartItem> cart) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("cart", cart);
        this.commonRedisFeginClient.insertCart(map);
    }
    //从购物车中获取商品
    private List<CartItem> getItemList(Map<String, CartItem> cart, String[] ids) {
        List<CartItem> list = new ArrayList<>();
        for (String id: ids) {
            list.add(cart.get(id));
        }
        return list;
    }
}
