package com.bjsxt.frontend.cart.service.impl;

import com.bjsxt.frontend.cart.feign.CommonRedisFeginClient;
import com.bjsxt.frontend.cart.service.UserCheckService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.cart.service.impl
 * @version: 1.0
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private CommonRedisFeginClient commonRedisFeginClient;
    @Override
    public TbUser checkUserToken(String token) {
        return this.commonRedisFeginClient.checkUserToken(token);
    }
}
