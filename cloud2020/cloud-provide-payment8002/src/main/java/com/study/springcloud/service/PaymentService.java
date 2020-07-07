package com.study.springcloud.service;

import com.study.springcloud.entities.Payment;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/5 13:30
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
