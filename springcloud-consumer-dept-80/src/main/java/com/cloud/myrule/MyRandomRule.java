package com.cloud.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class MyRandomRule extends AbstractLoadBalancerRule {

    private int total = 0; //被调用次数
    private int currentIndex = 0; //当前是谁在提供服务

//    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();  //获取或者的服务
                List<Server> allList = lb.getAllServers();  //获取全部服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                //=================================================

                if (total<5){
                    server = upList.get(currentIndex);
                    total++;
                }else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex>upList.size()-1){
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);
                }

                //=================================================


                if (server == null) {
                    Thread.yield();
                }

                if (server.isAlive()) {
                    return server;
                }

                server = null;
                Thread.yield();
            }

            return server;
        }
    }


    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
