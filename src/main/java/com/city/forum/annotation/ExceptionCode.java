package com.city.forum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cityforum
 * 自定义参数校验失败异常信息
 *
 * @author : chenDW
 * @date : 2021-09-12 16:27
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExceptionCode {

    /**
     * 响应码
     */
    int code() default 1002;

    /**
     * 响应信息
     */
    String msg() default "参数校验失败";
}
