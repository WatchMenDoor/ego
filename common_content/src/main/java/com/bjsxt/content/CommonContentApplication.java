package com.bjsxt.content;

/**
 * @Auther: liuxw
 * @Date: 2020-04-30
 * @Description: com.bjsxt.content
 * @version: 1.0
 */

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("com.bjsxt.mapper")
public class CommonContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonContentApplication.class, args);
    }
}
