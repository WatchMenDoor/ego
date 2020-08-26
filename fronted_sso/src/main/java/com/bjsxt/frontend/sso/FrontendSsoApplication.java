package com.bjsxt.frontend.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.sso
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.bjsxt.mapper")
public class FrontendSsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSsoApplication.class, args);
    }
}
