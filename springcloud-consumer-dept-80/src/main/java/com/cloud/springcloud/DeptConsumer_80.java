package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//在微服务启动的时候加载我们自定义的Ribbon类
public class DeptConsumer_80 {

    public static void main(String[] args){
        SpringApplication.run(DeptConsumer_80.class,args);
    }
}
