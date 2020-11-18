package com.cloud.springcloud.controller;

import com.cloud.springcloud.pojo.Dept;
import com.cloud.springcloud.service.DeptService;
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

//    获取一些配置信息，得到具体的微服务
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

//    注册进来的微服务，获取一些信息
    @GetMapping("/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discover=>services:"+services);

        //得到具体的微服务信息,通过具体的微服务id
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance instance : instances){
            System.out.println(
                            instance.getHost()+"\t"+
                            instance.getPort()+"\t"+
                            instance.getUri()+"\t"+
                            instance.getServiceId()
            );
        }
        return this.client;
    }

}
