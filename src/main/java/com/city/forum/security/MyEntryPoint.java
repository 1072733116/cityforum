package com.city.forum.security;

import com.city.forum.data.Result;
import com.city.forum.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * cityforum
 * 认证错误处理器
 *
 * @author : chenDW
 * @date : 2021-09-13 00:07
 **/
@Slf4j
public class MyEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result<String> result = new Result<>(ResultCode.UNAUTHORIZED, "没有登录");
        out.write(result.toString());
        out.flush();
        out.close();
    }
}
