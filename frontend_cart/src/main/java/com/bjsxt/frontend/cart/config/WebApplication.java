package com.bjsxt.frontend.cart.config;

import com.bjsxt.frontend.cart.inteceptor.UserLogininInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.frontend.cart.config
 * @version: 1.0
 */
//拦截器配置类
@Configuration
public class WebApplication implements WebMvcConfigurer {

    @Autowired
    private UserLogininInteceptor userLogininInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(this.userLogininInteceptor);
        //拦截哪个url
        registration.addPathPatterns("/cart/goSettlement/**");

    }
}
