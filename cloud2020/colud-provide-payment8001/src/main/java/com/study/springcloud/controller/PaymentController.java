package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonrRsult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping(value = "/payment/create")
    public CommonrRsult create(@RequestBody Payment payment){
        log.info("payment==>[{}]", payment.getSerial());
        int result = paymentService.create(payment);
        log.info("result==>[{}]", result);
        if (result > 0) {
            return new CommonrRsult(200, "插入成功", result);
        } else {
            return new CommonrRsult(888, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonrRsult create(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("payment==>[{}]", payment);
        if (null != payment) {
            return new CommonrRsult(200, "查询成功", payment);
        } else {
            return new CommonrRsult(888, "查询失败", null);
        }
    }

}
