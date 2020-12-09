package com.study.springcloud.myloadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/21 19:53
 */
public interface MyLoadBalance {

    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);

}
