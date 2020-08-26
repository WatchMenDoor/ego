package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.common.redis.controller
 * @version: 1.0
 */
/*
* 缓存用户登录*/
@RestController
@RequestMapping("/sso/redis")
public class SSOController {


    @Autowired
    private SSOService ssoService;
    //将用户缓存到redis中
    @RequestMapping("/insertUser")
    public void insertUser(@RequestBody TbUser tbUser,@RequestParam String Token){
        this.ssoService.insertUser(tbUser, Token);
    }

    //将用户从redis中移除
    @RequestMapping("/removeUser")
    public void removeUser(@RequestParam String Token){
           this.ssoService.removeUser(Token);
    }

    /*根据用户token检查用户是否在redis中失效
    * */
    @RequestMapping("/checkUserToken")
    public TbUser checkUserToken(@RequestParam String token){
        return this.ssoService.checkUserToken(token);
    }

}
