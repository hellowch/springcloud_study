package com.cloud.springcloud.service;


import com.cloud.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//fegin的负载均衡接口
@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {

    @RequestMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id);

    @RequestMapping("/dept/list")
    public List<Dept> queryAll();

    @RequestMapping("/dept/add")
    public Boolean addDept(Dept dept);
}
