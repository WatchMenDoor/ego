package com.bjsxt.common.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Auther: liuxw
 * @Date: 2020-05-09
 * @Description: com.bjsxt.common.zuul
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
public class CommonZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonZuulApplication.class, args);
    }
}
