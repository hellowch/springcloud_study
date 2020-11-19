package com.cloud.springcloud.service;


import com.cloud.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//Hystrix的失败回调接口工厂
@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory {

    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            public Dept queryById(Long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("id=>"+id+"没有对应的信息，客户端提供降级信息，该服务关闭")
                        .setDb_source("没有数据");
            }

            public List<Dept> queryAll() {
                return null;
            }

            public Boolean addDept(Dept dept) {
                return null;
            }
        };
    }
}
