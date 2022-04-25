package com.spt.springboot.util;

import com.spt.springboot.exceptions.ParamsException;

import java.lang.reflect.Parameter;

public class AssertUtil {

    /**
     * 判断结果是否为true
     *      如果结果为true，抛出异常
     * @param flag
     * @param msg
     * @return
     */
    public static void isTrue(boolean flag, String msg) {
        if(flag) {
            throw new ParamsException(msg);
        }
    }

}
