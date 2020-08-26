package com.bjsxt.frontend.sso.service;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.sso.service
 * @version: 1.0
 */
public interface SSOService {

    Result checkUserInfo(String checkValue, Integer checkFlag);

    Result  userRegister(TbUser tbUser);

    Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Result logOut(String token);
}
