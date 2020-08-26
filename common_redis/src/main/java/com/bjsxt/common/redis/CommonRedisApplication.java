package com.bjsxt.common.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CommonRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonRedisApplication.class, args);
    }
}
