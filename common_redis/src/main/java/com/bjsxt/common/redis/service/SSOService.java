package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbUser;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-05-07
 * @Description: com.bjsxt.common.redis.service
 * @version: 1.0
 */
public interface SSOService {

     void insertUser(TbUser tbUser, String Token);

     void removeUser( String Token);

     TbUser checkUserToken(@RequestParam String token);
}
