package com.cloud.springcloud.config;

import com.cloud.myrule.MyRandomRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    //为了让消费者的controller可以获得到服务提供者的server
    @Bean
    @LoadBalanced  //配置负载均衡实现 Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    //自定义负载均衡策略,RandomRule()为随机，不自定义则按顺序轮动
    @Bean
    public IRule myRule(){
        return new MyRandomRule();
    }

}
