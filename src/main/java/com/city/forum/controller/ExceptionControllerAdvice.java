package com.city.forum.controller;

import com.city.forum.annotation.ExceptionCode;
import com.city.forum.data.Result;
import com.city.forum.enums.ResultCode;
import com.city.forum.exception.ApiException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

/**
 * cityforum
 * 全局处理类
 *
 * @author : chenDW
 * @date : 2021-09-12 16:48
 **/
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 参数校验异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        //从异常对象中拿到错误信息
        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        //拿到错误的字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();
        // 参数的Class对象，通过字段名称获取Field对象
        Class<?> parameterType = e.getParameter().getParameterType();
        //获取校验失败的字段对象
        Field field = parameterType.getDeclaredField(fieldName);
        //获取field上的注解
        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);
        if (annotation != null) {
            return new Result<>(annotation.code(), annotation.msg(), errorMsg);
        }
        return new Result<>(ResultCode.VALIDATE_FAIL, errorMsg);
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(ApiException.class)
    public Result<String> apiExceptionHandler(ApiException e) {
        return new Result<>(ResultCode.FAILED, e.getMsg());
    }

    /**
     * 找不到用户异常
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<String> UsernameNotFoundExceptionHander(UsernameNotFoundException e) {
        return new Result<>(ResultCode.FAILED, e.toString());
    }
}
