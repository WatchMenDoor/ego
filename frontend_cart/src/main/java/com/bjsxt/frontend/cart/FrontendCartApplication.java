package com.bjsxt.frontend.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.frontend.cart
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FrontendCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendCartApplication.class, args);
    }
}
