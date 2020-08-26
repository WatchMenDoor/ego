package com.bjsxt.frontend.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.frontend.search
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bjsxt.mapper")
public class FrontendSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSearchApplication.class, args);
    }
}
