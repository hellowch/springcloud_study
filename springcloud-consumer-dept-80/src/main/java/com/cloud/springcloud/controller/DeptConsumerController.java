package com.cloud.springcloud.controller;

import com.cloud.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
public class DeptConsumerController {

    @Autowired
    private RestTemplate restTemplate;

//    private static final String REST_URL_PREFIX="http://localhost:8001";
    //通过ribbon实现负载均衡的时候，地址应写为一个变量，通过服务名访问
    private static final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping("/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }
}
