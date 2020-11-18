package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//在微服务启动的时候加载我们自定义的Ribbon类
@EnableFeignClients(basePackages = {"com.cloud.springcloud"})
//@ComponentScan("com.cloud.springcloud")
public class FeignDeptConsumer_80 {

    public static void main(String[] args){
        SpringApplication.run(FeignDeptConsumer_80.class,args);
    }
}
