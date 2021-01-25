package com.study.springcloud.service.impl;

import com.study.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/22 20:22
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfoOk(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo成功id==>[{}]" + id;
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfoTimeOut超时id==>[{}]" + id + "耗时 " + time + "（秒）";
    }
}
