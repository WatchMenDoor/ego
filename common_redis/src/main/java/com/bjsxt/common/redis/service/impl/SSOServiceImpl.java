package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.common.redis.service.impl
 * @version: 1.0
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${user_session_redis_key}")
    private String user_session_redis_key;



    @Override
    public void insertUser(TbUser tbUser, String Token) {
        //安全性将密码置空
           tbUser.setPassword("");
           this.redisTemplate.opsForValue().set(this.user_session_redis_key+":"+Token, tbUser,1, TimeUnit.DAYS);

    }


    //用户退出登录
    @Override
    public void removeUser(String Token) {
        this.redisTemplate.delete(this.user_session_redis_key+":"+ Token);
    }


    //根据用户token判断用户在redis中是否失效
    @Override
    public TbUser checkUserToken(String token) {
        return (TbUser) this.redisTemplate.opsForValue().get(this.user_session_redis_key+":"+token);
    }
}
