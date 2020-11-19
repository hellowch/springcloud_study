package com.cloud.springcloud.controller;

import com.cloud.springcloud.pojo.Dept;
import com.cloud.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

////    获取一些配置信息，得到具体的微服务
//    @Autowired
//    private DiscoveryClient client;
//
//    @PostMapping("/add")
//    public boolean addDept(Dept dept){
//        return deptService.addDept(dept);
//    }

    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if (dept == null){
            throw new RuntimeException("id=>"+id+",不存在该用户，或信息无法找到");
        }
        return dept;
    }

    //备选方案
    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id=>"+id+"没有对应的信息,null--@hystrix")
                .setDb_source("no this database in mysql");
    }

//    @GetMapping("/list")
//    public List<Dept> queryAll(){
//        return deptService.queryAll();
//    }
//
////    注册进来的微服务，获取一些信息
//    @GetMapping("/discovery")
//    public Object discovery(){
//        //获取微服务列表的清单
//        List<String> services = client.getServices();
//        System.out.println("discover=>services:"+services);
//
//        //得到具体的微服务信息,通过具体的微服务id
//        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
//
//        for (ServiceInstance instance : instances){
//            System.out.println(
//                            instance.getHost()+"\t"+
//                            instance.getPort()+"\t"+
//                            instance.getUri()+"\t"+
//                            instance.getServiceId()
//            );
//        }
//        return this.client;
//    }

}
