package com.spt.springboot;

import com.spt.springboot.exceptions.ParamsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlerResolver {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map exceptionHandler(Exception e) {
        Map<String,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg","系统异常,请重试!");

        // 判断是否是指定的异常
        if (e instanceof ParamsException) {
            ParamsException p = (ParamsException) e;
            map.put("code",p.getCode());
            map.put("msg",p.getMsg());
        }

        return map;
    }

    /**
     * 处理指定的异常
     * @param p
     * @return
     */
    @ExceptionHandler(value = ParamsException.class)
    @ResponseBody
    public Map exceptionHandler02(ParamsException p) {
        Map<String,Object> map = new HashMap<>();
        map.put("code",p.getCode());
        map.put("msg",p.getMsg());

        return map;
    }

    /**
     *  处理数据校验异常
     * @param b
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Map exceptionHandler03(BindException b) {
        Map<String,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg",b.getBindingResult().getFieldError().getDefaultMessage());
        return map;
    }
}
