package com.city.forum.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(1000,"操作成功"),

    FAILED(1001,"操作失败"),

    VALIDATE_FAIL(1002,"参数校验失败"),

    ERROR(1003,"未知错误"),

    UNAUTHORIZED(1004, "没有登录"),

    FORBIDDEN(1005, "没有相关权限");
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
