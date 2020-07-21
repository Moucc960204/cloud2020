package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonrRsult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/5 13:35
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonrRsult create(@RequestBody Payment payment) {
        log.info("payment==>[{}]", payment.getSerial());
        int result = paymentService.create(payment);
        log.info("result==>[{}]", result);
        if (result > 0) {
            return new CommonrRsult(200, "插入成功, serverPort:" + serverPort, result);
        } else {
            return new CommonrRsult(888, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonrRsult create(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("payment==>[{}]", payment);
        if (null != payment) {
            return new CommonrRsult(200, "查询成功, serverPort:" + serverPort, payment);
        } else {
            return new CommonrRsult(888, "查询失败", null);
        }
    }

    @GetMapping(value = "/payment/disCovery")
    public Object disCovery() {
        List<String> services = discoveryClient.getServices();
        for (int i = 0; i < services.size(); i++) {
            log.info("service==>[{}]", services.get(i));
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("ServiceId==>[{}], Host==>[{}], Port==>[{}], Uri==[{}] ", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/loadBalance")
    public String getPaymentLoadBalance() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
