package com.city.forum.exception;

import lombok.Getter;

/**
 * cityforum
 *  自定义异常
 * @author : chenDW
 * @date : 2021-09-12 15:30
 **/
@Getter
public class ApiException extends RuntimeException{

    /**
     * 异常状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    public ApiException(){
        this(1001,"接口错误");
    }

    public ApiException(String msg){
        this(1001,msg);
    }

    public ApiException(Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
