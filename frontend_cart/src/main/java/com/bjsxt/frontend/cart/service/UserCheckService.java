package com.bjsxt.frontend.cart.service;

import com.bjsxt.pojo.TbUser;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.cart.service
 * @version: 1.0
 */
public interface UserCheckService {


    TbUser checkUserToken(String token);
}
