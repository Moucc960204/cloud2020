package com.study.springboot.service;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/22 20:21
 */
public interface PaymentService {

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    String paymentInfoOk(Integer id);

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    String paymentInfoTimeOut(Integer id);

}
