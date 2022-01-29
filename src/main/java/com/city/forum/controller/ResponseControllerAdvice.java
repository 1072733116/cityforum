package com.city.forum.controller;

import com.city.forum.data.Result;
import com.city.forum.exception.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * cityforum
 * 全局处理响应数据
 * 优化接口返回统一响应体 + 异常也返回统一响应体
 *
 * @author : chenDW
 * @date : 2021-09-12 15:19
 **/
@RestControllerAdvice(basePackages = "com.city.forum.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 如果接口返回的类型本身就是Result类型那就没有必要进行额外的操作，返回false
        return !methodParameter.getParameterType().equals(Result.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (methodParameter.getParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new Result<>(data));
            } catch (JsonProcessingException e) {
                return new ApiException("返回String类型错误");
            }
        }
        //将原本的数据包装在Result里
        return new Result<>(data);
    }
}
