package com.study.springboot.controller;

import com.study.springboot.service.PaymentFeignService;
import com.study.springcloud.entities.CommonrRsult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/21 23:03
 */
@RestController
@Slf4j
public class OrderFeginController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonrRsult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPayment(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        // openfeign-ribbon 客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }

}
