package com.bjsxt.frontend.cart.service.impl;

import com.bjsxt.frontend.cart.feign.CommonItemFeignClient;
import com.bjsxt.frontend.cart.service.CookieCartService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.CartItem;
import com.bjsxt.utils.CookieUtils;
import com.bjsxt.utils.JsonUtils;
import com.bjsxt.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.cart.service.impl
 * @version: 1.0
 */
//用户未登录下的购物车操作
@Service
public class CookieCartServiceImpl implements CookieCartService {




    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;


    //用户未登录添加商品到购物车
    @Override
    public Result addItem(Long itemId,  Integer num, HttpServletRequest request, HttpServletResponse response) {
        //1.获取临时购物车

        Map<String, CartItem> cart = this.getCart(request);

        //2.查询商品
        TbItem item = this.selectItemById(itemId);

        //3.向购物车中添加商品
        this.addItemToCart(cart,item,num,itemId);

        //4.将购物车通过cookie写回给客户端浏览器
         this.addClientCookie(request,response,cart);
        return Result.ok();
    }


    //查看购物车
    @Override
    public Result showCart(String userId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> list = new ArrayList<>();
        Map<String, CartItem> cart = this.getCart(request);
        Set<String> keys = cart.keySet();
        for (String key: keys) {
            list.add(cart.get(key));
        }


        return Result.ok(list);
    }


    //修改购物车中商品的数量
    @Override
    public Result updateItemNum(Integer num, String userId, Long itemId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = this.getCart(request);
        CartItem cartItem = cart.get(itemId);
        if (cartItem!=null){
            cartItem.setNum(num);
        }
        this.addClientCookie(request, response, cart);
        return Result.ok();
    }


    //删除购物车中的商品
    @Override
    public Result deleteItemFromCart(String userId, Long itemId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = this.getCart(request);
        cart.remove(itemId);
        this.addClientCookie(request, response, cart);
        return Result.ok();
    }


    //获取购物车
    private Map<String, CartItem> getCart(HttpServletRequest request) {

        String cartJson = CookieUtils.getCookieValue(request, this.cart_cookie_name, true);
        if (StringUtils.isBlank(cartJson)){
            //不存在临时购物车
            return new HashMap<String, CartItem>();
        }
        try{
            //临时购物车已存在，那么需要json转换
            Map<String,CartItem> map = JsonUtils.jsonToMap(cartJson, CartItem.class);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();

    }
    //根据商品id查询商品
    private TbItem selectItemById(Long itemId) {
         return  this.commonItemFeignClient.selectItemInfo(itemId);
    }
    //将商品添加到购物车中
    private void addItemToCart(Map<String, CartItem> cart, TbItem item, Integer num, Long itemId) {
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

    //将购物车通过cookie写回给客户端浏览器
    private void addClientCookie(HttpServletRequest request, HttpServletResponse response, Map<String, CartItem> cart) {

        String cartJson = JsonUtils.objectToJson(cart);
        CookieUtils.setCookie(request, response, this.cart_cookie_name, cartJson,true);
    }
}
