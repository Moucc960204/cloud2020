package com.study.springcloud.dao;

import com.study.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/5 13:09
 */

@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
