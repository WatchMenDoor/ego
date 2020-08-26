package com.bjsxt.frontend.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: liuxw
 * @Date: 2020-05-08
 * @Description: com.bjsxt.frontend.order
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@EnableFeignClients
@MapperScan("com.bjsxt.mapper")
public class FrontendOrederApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendOrederApplication.class, args);
    }
}
