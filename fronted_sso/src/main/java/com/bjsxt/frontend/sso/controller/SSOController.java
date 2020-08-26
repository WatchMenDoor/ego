package com.bjsxt.frontend.sso.controller;

import com.bjsxt.frontend.sso.service.SSOService;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.sso.controller
 * @version: 1.0
 */
//用户注册与登录
@RestController
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private SSOService ssoService;

    //对用户注册信息进行校验
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable Integer checkFlag){
        try{
            return this.ssoService.checkUserInfo(checkValue, checkFlag);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //实现用户注册
    @RequestMapping("/userRegister")
    public Result  userRegister(TbUser tbUser){
        try{
            return this.ssoService.userRegister(tbUser);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //实现用户登录
    @RequestMapping("/userLogin")
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try{
            return this.ssoService.userLogin(username, password,request);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //实现用户退出登录
    @RequestMapping("/logOut")
    public Result logOut(String token){
        try{
            return this.ssoService.logOut(token);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
