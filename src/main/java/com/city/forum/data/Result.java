package com.city.forum.data;

import com.city.forum.enums.ResultCode;
import lombok.Getter;

/**
 * cityforum
 * 自定义统一响应体
 *
 * @author : chenDW
 * @date : 2021-09-12 15:09
 **/
@Getter
public class Result<T> {
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultCode resultCode,T data){
        this(resultCode.getCode(),resultCode.getMsg(),data);
    }

    public Result(T data) {
        this(ResultCode.SUCCESS,data);
    }

    @Override
    public String toString() {
        return String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":\"%s\"}", code, msg, data.toString());
    }
}
