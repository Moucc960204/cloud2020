package com.study.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenchen.mou
 * @Description:
 * @Date: create in 2020/7/5 15:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonrRsult<T> {

    private Integer code;
    private String  message;
    private T data;

    public CommonrRsult(Integer code, String message){
        this(code, message, null);
    }

}
