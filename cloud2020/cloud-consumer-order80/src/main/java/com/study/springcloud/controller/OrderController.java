package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonrRsult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.myloadbalance.MyLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/5 15:06
 */

@RestController
@Slf4j
public class OrderController {

    //private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private MyLoadBalance myloadBalance;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonrRsult<Payment> create(Payment payment) {
        log.info("payment==>[{}]", payment.getSerial());
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonrRsult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonrRsult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("222");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonrRsult.class);
    }

    @GetMapping(value = "/consumer/payment/loadBalance")
    public String getPaymentLoadBalance() {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        if (instanceList == null || instanceList.size() <= 0){
            return null;
        }

        ServiceInstance serviceInstance = myloadBalance.instance(instanceList);
        URI uri = serviceInstance.getUri();
        log.info("uri==>[{}]", uri);
        return restTemplate.getForObject(uri + "/payment/loadBalance", String.class);
    }

}
