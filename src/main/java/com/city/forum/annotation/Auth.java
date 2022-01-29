package com.city.forum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cityforum
 * 权限注解，标识需要权限处理的接口
 *
 * @author : chenDW
 * @date : 2021-09-13 00:14
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface Auth {
    /**
     * 权限id，模块id + 方法id
     */
    int id();

    /**
     *权限名称
     */
    String name();
}
