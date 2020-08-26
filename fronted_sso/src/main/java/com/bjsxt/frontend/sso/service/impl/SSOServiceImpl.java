package com.bjsxt.frontend.sso.service.impl;

import com.bjsxt.frontend.sso.fegin.CommonRedisFeign;
import com.bjsxt.frontend.sso.service.SSOService;
import com.bjsxt.mapper.TbUserMapper;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.pojo.TbUserExample;
import com.bjsxt.utils.*;
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
 * @Description: com.bjsxt.frontend.sso.service.impl
 * @version: 1.0
 */
//用户注册与登录
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private CommonRedisFeign commonRedisFeign;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    //用户注册信息验证
    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (checkFlag==1){
            criteria.andUsernameEqualTo(checkValue);
        }else if(checkFlag==2){
            criteria.andPhoneEqualTo(checkValue);
        }
        int i = this.tbUserMapper.countByExample(example);
        if (i>0){
            return Result.error("数据已存在");
        }
        return Result.ok(checkValue);
    }

    //实现用户注册
    @Override
    public Result userRegister(TbUser tbUser) {
        //对密码进行加密
        String password = tbUser.getPassword();
        String digest = MD5Utils.digest(password);
        tbUser.setPassword(digest);
        //数据补齐
        Date date = new Date();
        tbUser.setCreated(date);
        tbUser.setUpdated(date);
        int insert = this.tbUserMapper.insert(tbUser);
        if (insert>0){
            return Result.ok();
        }

        return Result.error("注册用户失败");
    }
     //用户登录
    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //根据用户名和密码进行查询

        TbUser tbUser = this.login(username,password);
        if (tbUser==null){
            return Result.error("用户名或密码错误");
        }

        //查到后添加到redis中
        String token = UUID.randomUUID().toString();
        Integer integer = this.insertIntoRedis(tbUser, token);
        if (integer==500){
          return Result.error("登陆失败");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token", token);
        map.put("userid", tbUser.getId().toString());
        map.put("username", tbUser.getUsername());

        //将临时购物车中的商品同步到永久购物车
        this.synCart(tbUser.getId().toString(),request);
        //同步成功后删除临时购物车
        this.deleteCookieCart(request,response);

        return Result.ok(map);
    }




    //实现用户退出登录
    @Override
    public Result logOut(String token) {
        try{
            this.commonRedisFeign.removeUser(token);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("用户退出登录失败");
    }


    //用户登录业务处理
    private TbUser login(String username, String password) {
        String digest = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(digest);
        List<TbUser> tbUsers = this.tbUserMapper.selectByExample(example);
        if (tbUsers==null||tbUsers.size()<0){
            return null;
        }
        return tbUsers.get(0);

    }
    //将用户添加到redis中
    private Integer insertIntoRedis(TbUser tbUser, String token) {

       try{
           this.commonRedisFeign.insertUser(tbUser, token);
           return 200;
       }catch (Exception e){
           e.printStackTrace();
       }
       return 500;
    }
    //同步购物车
    private void synCart(String userId, HttpServletRequest request) {

        //获取临时购物车
        Map<String, CartItem> CookieCart = this.getCart(request);
        //获取永久购物车
        Map<String, CartItem> redisCart = this.getCart(userId);
        //删除永久购物车中包含临时购物车中的商品
        Set<String> keys = CookieCart.keySet();
        for (String key: keys) {
            redisCart.remove(key);
        }
        //将临时购物车中的商品添加到永久购物车中
        redisCart.putAll(CookieCart);
        //将永久购物车缓存到redis中
        this.addClientToRedis(userId,redisCart);

    }

    //获取临时购物车
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

    //根据用户id取出永久购物车
    private Map<String, CartItem> getCart(String userId) {
        try {
            Map<String, CartItem> cart = this.commonRedisFeign.selectCart(userId);
            //第一次登录查不到
            if (cart==null){
                cart = new HashMap<String, CartItem>();
            }
            return cart;
        }catch (Exception e){

        }
        return new HashMap<String, CartItem>();
    }

    //将永久购物车缓存到redis中
    private void addClientToRedis(String userId, Map<String, CartItem> cart) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("cart", cart);
        this.commonRedisFeign.insertCart(map);
    }
    //同步成功后删除临时购物车
    private void deleteCookieCart(HttpServletRequest request, HttpServletResponse response) {
      CookieUtils.deleteCookie(request, response, this.cart_cookie_name);
    }
}
